ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "LeaguePain",
    scalacOptions += "-Ymacro-annotations",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.4",
    libraryDependencies += "org.scala-lang" %% "toolkit" % "0.1.7",
    libraryDependencies += "com.fazecast" % "jSerialComm" % "2.9.3",
    libraryDependencies += "org.scalafx" %% "scalafx" % "20.0.0-R31",
    libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.5",
      assembly / mainClass := Some("Main"),
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", _*) => MergeStrategy.discard
      case _                        => MergeStrategy.first
    }
  )



