ThisBuild / scalaVersion     := "2.13.5"

lazy val root = (project in file("."))
  .settings(
    name := "scoverage-macro-repoducer",
    scalacOptions += "-Ymacro-annotations",
    libraryDependencies ++= Seq(
      "dev.zio"                       %% "zio-test"     % "1.0.9" % Test,
      "dev.zio"                       %% "zio-test-sbt"     % "1.0.9" % Test,
      "dev.zio"                       %% "zio-macros"     % "1.0.9",
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
