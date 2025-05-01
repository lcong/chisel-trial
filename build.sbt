/*
// See README.md for license details.

ThisBuild / scalaVersion     := "2.13.15"
ThisBuild / version          := "0.1.0"
ThisBuild / organization     := "com.github.lcong"

val chiselVersion = "6.6.0"

lazy val root = (project in file("."))
  .settings(
    name := "chisel-trial",
    libraryDependencies ++= Seq(
      "org.chipsalliance" %% "chisel" % chiselVersion,
      "org.scalatest" %% "scalatest" % "3.2.16" % "test",
      "org.chipsalliance" %% "treadle" % "6.0.0" % "test"
      "org.chipsalliance" %% "circt" % "6.0.0",
     ),

    scalacOptions ++= Seq(
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit",
      "-Ymacro-annotations",
    ),

    addCompilerPlugin("org.chipsalliance" % "chisel-plugin" % "6.6.0" cross CrossVersion.full)

  )
*/

// build.sbt
ThisBuild / scalaVersion := "2.13.15"

// 国内用户可添加阿里云镜像（可选）
resolvers += "Aliyun" at "https://maven.aliyun.com/repository/public"

libraryDependencies ++= Seq(
  "org.chipsalliance" %% "chisel" % "6.6.0",
  "org.scalatest" %% "scalatest" % "3.2.16" % "test",
  "edu.berkeley.cs" %% "chiseltest" % "6.0.0",

)

scalacOptions ++= Seq(
  "-language:reflectiveCalls",
  "-deprecation",
  "-feature",
  "-Xcheckinit",
  "-Ymacro-annotations",
)


addCompilerPlugin("org.chipsalliance" % "chisel-plugin" % "6.6.0" cross CrossVersion.full)