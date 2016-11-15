name := """lambda-hero"""

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8"
)

lazy val root = project in file(".")

lazy val list = project.in(file("./list")).enablePlugins(Play)
    .settings(commonSettings: _*)
    .settings(libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
      "com.amazonaws" % "aws-lambda-java-events" % "1.3.0"
    ))
    .dependsOn(library % "test->test;compile->compile")


lazy val detail = project.in(file("./detail")).enablePlugins(Play)
    .settings(commonSettings: _*)
    .dependsOn(library % "test->test;compile->compile")


lazy val library = project.in(file("./library")).enablePlugins(Play)
    .settings(commonSettings: _*)
    .settings(libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk" % "1.10.1"
    ))
