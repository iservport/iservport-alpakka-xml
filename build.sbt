
organization := "com.iservport"

name := "alpakka-xml"

version := "0.1.DEV"

scalaVersion := "2.12.2"

mainClass in Compile := Some("com.iservport.xml.Application")

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging)
  .enablePlugins(DockerPlugin)
  .settings(
    packageName in Docker := "iservport/alpakka-xml",
    dockerBaseImage := "anapsix/alpine-java:latest",
    dockerUpdateLatest := true,
    dockerExposedPorts := Seq(8085),
    dockerExposedVolumes := Seq("/opt/data")
  )

libraryDependencies ++= Seq(
  "com.lightbend.akka"          %% "akka-stream-alpakka-file"       % "0.9",
  "com.lightbend.akka"          %% "akka-stream-alpakka-xml"        % "0.9",
  "org.mongodb"                 %% "casbah"                         % "3.1.1"
)

