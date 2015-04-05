name := """zmqnative"""

version := "1.0"

scalaVersion := "2.11.5"

resolvers += "mDialog releases" at "http://mdialog.github.io/releases/"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
	"org.scalatest" %% "scalatest" % "2.2.4" % "test",
	"com.mdialog" %% "scala-zeromq" % "1.1.0",
	"org.zeromq" % "jeromq" % "0.3.4"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.9"

