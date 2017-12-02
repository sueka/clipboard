import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "me.sueka",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Clipboard",
    description := "",
    libraryDependencies ++= Seq(
      scalaz,
      scalazEffect
    )
  )