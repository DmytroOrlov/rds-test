lazy val playSlickV = "3.0.3"

lazy val `rds-test` = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.12.5",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0",
      "io.monix" %% "monix" % "2.3.2",
      "com.typesafe.play" %% "play-slick" % playSlickV,
      "com.typesafe.play" %% "play-slick-evolutions" % playSlickV,
      "org.postgresql" % "postgresql" % "42.2.0",
      "com.h2database" % "h2" % "1.4.196",

      "org.scalatest" %% "scalatest" % "3.2.0-SNAP9" % Test,
      "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test,
      "org.scalacheck" %% "scalacheck" % "1.13.5" % Test,
      "org.scalamock" %% "scalamock-scalatest-support" % "3.6.0" % Test,
      "org.mockito" % "mockito-core" % "2.13.0" % Test
    )
  )
