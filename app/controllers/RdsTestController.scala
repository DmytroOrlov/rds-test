package controllers

import javax.inject.{Inject, Singleton}

import akka.stream.scaladsl.Source
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc._
import slick.jdbc.JdbcProfile

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Success, Try}

sealed trait TickTack {
  def count: Int
}

case class WriteTick(count: Int, state: Try[Seq[Int]]) extends TickTack

case class ReadTack(count: Int, e: Option[Throwable]) extends TickTack

@Singleton
class RdsTestController @Inject()
(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)
(implicit ec: ExecutionContext) extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

  import dbConfig.profile.api._

  def root = Action {
    val session = System.currentTimeMillis()
    val s = Source.tick(1.second, 1.second, 'ignore)
      .scanAsync(WriteTick(0, Success(Nil)): TickTack) {
        case (WriteTick(i, _), _) =>
          db.run(sqlu"insert into rds_state values($session, $i)").map(_ =>
            ReadTack(i + 1, None)
          ).recover { case e => ReadTack(i, Some(e)) }
        case (ReadTack(i, _), _) =>
          db.run(sql"select counter from rds_state where session_id = $session".as[Int]).map(ss =>
            WriteTick(i, Success(ss))
          ).recover { case e => ReadTack(i, Some(e)) }
      }
    Ok.chunked(s.map(v => s"$v\n"))
  }

  def health = Action(Ok("{}"))
}
