name := "projet_progFL"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies += "com.lihaoyi" %% "requests" % "0.6.9"
libraryDependencies +="com.lihaoyi" %% "upickle" % "1.4.2"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.30.0"
libraryDependencies  ++= Seq(
  // Last stable release
  "org.scalanlp" %% "breeze" % "1.3",

  // The visualization library is distributed separately as well.
  // It depends on LGPL code
  "org.scalanlp" %% "breeze-viz" % "1.3"
)

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"
