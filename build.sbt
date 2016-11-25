name := """lambda-hero"""

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  assemblyMergeStrategy in assembly := {
    case PathList("javax", "servlet", xs@_*) => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".properties" => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".xml" => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".types" => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".class" => MergeStrategy.first
    case "application.conf" => MergeStrategy.concat
    case "unwanted.txt" => MergeStrategy.discard
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  },
  scalaSource in Compile <<= (baseDirectory in Compile) (_ / "app"),
  scalaSource in Test <<= (baseDirectory in Test) (_ / "test")
)

lazy val root = (project in file("."))
    .aggregate(external, library)

lazy val external = project.in(file("./external"))
    .settings(commonSettings: _*)
    .settings(
      name := "lambda-hero-external",
      version := "0.1.0",
      assemblyJarName in assembly := s"${ name.value }-${ version.value }.jar"
    )
    .settings(libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
      "com.amazonaws" % "aws-lambda-java-events" % "1.3.0"
    ))
    .dependsOn(library % "test->test;compile->compile")


lazy val library = project.in(file("./library"))
    .settings(commonSettings: _*)
    .settings(libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk" % "1.10.1"
    ))
    .settings(
      name := "lambda-hero-library",
      version := "0.1.0",
      assemblyJarName in assembly := s"${ name.value }-${ version.value }.jar"
    )
