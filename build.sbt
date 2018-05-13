import sbt._

val scalazVersion = "7.2.17"
val scalaTestVersion = "3.0.4"

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
    "com.typesafe" % "config" % "1.3.1",
    "org.scalaz" %% "scalaz-core" % scalazVersion,
    "org.scalaz" %% "scalaz-effect" % scalazVersion,
    "org.scalactic" %% "scalactic" % scalaTestVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % Test
  ),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked"
  )
)
