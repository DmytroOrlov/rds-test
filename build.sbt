lazy val playSlickV = "3.0.3"

lazy val `rds-test` = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.12.6",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
      "io.monix" %% "monix" % "3.0.0-RC1",
      "com.typesafe.play" %% "play-slick" % playSlickV,
      "com.typesafe.play" %% "play-slick-evolutions" % playSlickV,
      "org.postgresql" % "postgresql" % "42.2.2",
      "com.h2database" % "h2" % "1.4.197",

      "org.scalatest" %% "scalatest" % "3.2.0-SNAP10" % Test,
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
      "org.scalacheck" %% "scalacheck" % "1.14.0" % Test,
      "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test,
      "org.mockito" % "mockito-core" % "2.16.0" % Test
    )
  )
