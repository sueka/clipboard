import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "me.sueka",
      scalaVersion := "2.12.4",
      version := "0.2.1-SNAPSHOT"
    )),
  name := "Clipboard",
  description := "A Scala library to set, get, and modify the system clipboard without side effects",
  libraryDependencies ++= Seq(
    typesafeConfig,
    scalaz,
    scalazEffect,
    scalactic,
    scalaTest % Test
  ),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked"
  )
)
