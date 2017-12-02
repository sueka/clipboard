import sbt._

object Dependencies {
  private val scalazVersion = "7.2.17"

  lazy val scalaz = "org.scalaz" %% "scalaz-core" % scalazVersion
  lazy val scalazEffect = "org.scalaz" %% "scalaz-effect" % scalazVersion
}
