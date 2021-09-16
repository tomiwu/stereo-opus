name := "stereo-opus"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.6.16"
libraryDependencies += "za.co.monadic"     %% "scopus"      % "0.5.0"

resolvers += "m2-dv8tion" at "https://m2.dv8tion.net/releases"

libraryDependencies += "club.minnced" % "opus-java-api"     % "1.1.0"
libraryDependencies += "club.minnced" % "opus-java-natives" % "1.1.0"
