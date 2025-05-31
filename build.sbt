import DocSettings.*
import Utils.*

ThisBuild / publish / skip := true
ThisBuild / publishArtifact := false

/*
 ***********************
 * Core Module *
 ***********************
 */

import sbt.*
import Keys.*
import sbt.Def.settings

import scala.collection.Seq

//lazy val root = project
//  .enablePlugins(NoPublishPlugin)
//  .in(file("."))
////  .aggregate(core, vision, examples, docs)
//  .settings(
//    javaCppVersion := (ThisBuild / javaCppVersion).value,
////    csrCacheDirectory := file("D:\\coursier"),
//  )

ThisBuild / tlBaseVersion := "0.0" // your current series x.y
//ThisBuild / CoursierCache := file("D:\\coursier")
ThisBuild / organization := "io.github.mullerhai" //"dev.storch"
ThisBuild / organizationName := "storch.dev"
ThisBuild / startYear := Some(2024)
ThisBuild / licenses := Seq(License.Apache2)
ThisBuild / developers := List(
  // your GitHub handle and name
  tlGitHubDev("mullerhai", "mullerhai")
)
ThisBuild / version := "0.1.0"

ThisBuild / scalaVersion := "3.6.4"
ThisBuild / tlSonatypeUseLegacyHost := false

import xerial.sbt.Sonatype.sonatypeCentralHost
ThisBuild / sonatypeCredentialHost := sonatypeCentralHost

import ReleaseTransformations._
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("+publishSigned"),
  releaseStepCommandAndRemaining("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges,
)


//ThisBuild / version := "0.1.0-SNAPSHOT"


ThisBuild / tlSitePublishBranch := Some("main")

ThisBuild / apiURL := Some(new URL("https://storch.dev/api/"))
ThisBuild / tlSonatypeUseLegacyHost := false

// publish website from this branch
ThisBuild / tlSitePublishBranch := Some("main")
ThisBuild / homepage := Some(new URL("https://storch.dev/api/"))
ThisBuild / scmInfo := Some( ScmInfo( url( "https://github.com/BigDataZhangSir/scala-polars" ), "scm:git:https://github.com/BigDataZhangSir/scala-polars.git" ) )
// https://mvnrepository.com/artifact/org.projectlombok/lombok

// https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.19.0"
// https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-scala
libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.19.0"
// https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.19.0"
// https://mvnrepository.com/artifact/org.typelevel/kind-projector
//libraryDependencies += ("org.typelevel" %% "kind-projector" % "0.13.3") cross CrossVersion.for3Use2_13
excludeDependencies +=  "org.typelevel" % "kind-projector"
lazy val core = project
  .in(file("core"))
  .withId("storch-polars")
  .settings(name := "storch-polars")
  .enablePlugins(GhpagesPlugin, SiteScaladocPlugin)
  .settings(
//    unidocSourceFilePatterns := Nil,
//    git.remoteRepo := "git@github.com:chitralverma/scala-polars.git",
//    SiteScaladoc / siteSubdirName := "api/latest"
  )
  .settings(ProjectDependencies.dependencies)
  .settings(GeneralSettings.commonSettings)
//  .settings(PublishingSettings.settings)
  .settings(
    nativeRoot := baseDirectory.value.toPath.resolveSibling("native").toFile,
    inConfig(Compile)(NativeBuildSettings.settings)
  )
  .settings(ExtraCommands.commands)
  .settings(ExtraCommands.commandAliases)
//  .configureUnidoc("scala-polars API Reference")

/*
 ***********************
 * Examples Module *
 ***********************
 */

lazy val examples = project
  .in(file("examples"))
  .withId("scala-polars-examples")
  .settings(name := "scala-polars-examples")
  .settings(GeneralSettings.commonSettings)
  .settings(
    Compile / packageBin / publishArtifact := false,
    Compile / packageDoc / publishArtifact := false,
    Compile / packageSrc / publishArtifact := false
  )
  .dependsOn(core)

ThisBuild  / assemblyMergeStrategy := {
  case v if v.contains("module-info.class")   => MergeStrategy.discard
  case v if v.contains("UnusedStub")          => MergeStrategy.first
  case v if v.contains("aopalliance")         => MergeStrategy.first
  case v if v.contains("inject")              => MergeStrategy.first
  case v if v.contains("jline")               => MergeStrategy.discard
  case v if v.contains("scala-asm")           => MergeStrategy.discard
  case v if v.contains("asm")                 => MergeStrategy.discard
  case v if v.contains("scala-compiler")      => MergeStrategy.deduplicate
  case v if v.contains("reflect-config.json") => MergeStrategy.discard
  case v if v.contains("jni-config.json")     => MergeStrategy.discard
  case v if v.contains("git.properties")      => MergeStrategy.discard
  case v if v.contains("reflect.properties")      => MergeStrategy.discard
  case v if v.contains("compiler.properties")      => MergeStrategy.discard
  case v if v.contains("scala-collection-compat.properties")      => MergeStrategy.discard
  case x =>
    val oldStrategy = (assembly / assemblyMergeStrategy).value
    oldStrategy(x)
}