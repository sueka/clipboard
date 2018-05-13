import sbt._

val scalaTestTest = ProjectRef(uri("git://github.com/dotty-staging/scalatest.git#%s".format("dotty")), "scalatest-test")
val scalactic = ProjectRef(uri("git://github.com/dotty-staging/scalatest.git#%s".format("dotty")), "scalactic")

val dottyVersion = dottyLatestNightlyBuild.get
val scalazVersion = "7.2.17"

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "me.sueka",
      scalaVersion := dottyVersion,
      version := "0.2.1-SNAPSHOT"
    )),
  name := "Clipboard",
  description := "A Scala library to set, get, and modify the system clipboard without side effects",
  libraryDependencies ++= Seq(
    "com.typesafe" % "config" % "1.3.1",
    ("org.scalaz" %% "scalaz-core" % scalazVersion).withDottyCompat(dottyVersion),
    ("org.scalaz" %% "scalaz-effect" % scalazVersion).withDottyCompat(dottyVersion)
  ),
  scalacOptions ++= Seq(
    "-deprecation",
    "-feature",
    "-unchecked"
  )
).dependsOn(scalaTestTest, scalactic)
