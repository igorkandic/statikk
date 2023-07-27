ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "LeaguePain",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.4",
    libraryDependencies += "org.scala-lang" %% "toolkit" % "0.1.7",
    libraryDependencies += "com.fazecast" % "jSerialComm" % "2.9.3"
  )


