name := "scala-eff-playground"
version := "1"

scalaVersion := "2.12.3"
scalaVersion in ThisBuild := "2.12.3"
fork := true

libraryDependencies ++= Seq(
  "org.atnos" %% "eff" % "4.6.1",
  "org.atnos" %% "eff-monix" % "4.6.1",
  "org.atnos" %% "eff-doobie" % "4.6.1",

  "org.typelevel" %% "cats" % "0.9.0",

  "io.monix" %% "monix" % "2.3.0",
  "io.monix" %% "monix-cats" % "2.3.0",

  "org.apache.commons" % "commons-csv" % "1.5",
  "commons-io" % "commons-io" % "2.5",

  "org.specs2" %% "specs2-core" % "4.0.1" % "test",
  "org.specs2" %% "specs2-scalacheck" % "4.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-encoding", "utf-8",
  "-explaintypes",
  "-deprecation",
  "-feature",
  "-Xlint",
  "-Xfatal-warnings",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-inaccessible",
  "-Ywarn-infer-any",
  "-Ywarn-nullary-override",
  "-Ywarn-nullary-unit",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:params",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-Ypartial-unification",
  "-Yno-adapted-args"
)

scalacOptions in (Compile, console) ~= (_.filterNot(Set(
  "-Ywarn-unused:imports",
  "-Xfatal-warnings"
)))

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
