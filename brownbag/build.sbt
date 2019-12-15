name := "brownbag"

version := "0.1"

scalaVersion := "2.11.7"

libraryDependencies ++=
  Seq("com.github.swagger-akka-http" % "swagger-akka-http_2.11" % "1.0.0",
  "com.typesafe.akka" % "akka-http-testkit_2.11" % "10.0.9" % "test")

// for JSON serialization/deserialization following dependency is required:
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.9"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test

val akkaVersion = "2.5.23"
val akkaHttpVersion = "10.1.9"

libraryDependencies += "com.typesafe.akka" %% "akka-http"   % akkaHttpVersion
libraryDependencies += "com.typesafe.akka" %% "akka-actor"  % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion

libraryDependencies += "com.typesafe.akka" %% "akka-slf4j" % "2.5.23"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

// for Loan Pattern Joshua Suereth’s ARM library.
// 'managed' method automatically closes the file.
// libraryDependencies += "com.jsuereth" %% "scala-arm" % "2.0"