import sbt._

object Dependencies {
  private val scalazVersion = "7.2.17"
  private val scalaTestVersion = "3.0.4"

  lazy val scalaz = "org.scalaz" %% "scalaz-core" % scalazVersion
  lazy val scalazEffect = "org.scalaz" %% "scalaz-effect" % scalazVersion
  lazy val scalactic = "org.scalactic" %% "scalactic" % scalaTestVersion
  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestVersion
}
