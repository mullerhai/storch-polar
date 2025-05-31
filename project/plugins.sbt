addSbtPlugin("com.github.sbt" % "sbt-jni" % "1.7.1")

addSbtPlugin("com.lightbend.sbt" % "sbt-java-formatter" % "0.8.0")

////addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "2.2.2")

//addSbtPlugin("com.timushev.sbt" % "sbt-updates" % "0.6.4")

/////addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "2.3.0")

//addSbtPlugin("no.arktekk.sbt" % "aether-deploy" % "0.29.1")

//addSbtPlugin("com.github.sbt" % "sbt-dynver" % "5.1.0")

//addSbtPlugin("com.github.sbt" % "sbt-ghpages" % "0.8.0")

////addSbtPlugin("com.github.sbt" % "sbt-unidoc" % "0.5.0")

//addSbtPlugin("com.thoughtworks.sbt-api-mappings" % "sbt-api-mappings" % "3.0.2")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.5.2")
addSbtPlugin("org.bytedeco" % "sbt-javacpp" % "1.17")
addSbtPlugin("org.scalameta" % "sbt-mdoc" % "2.5.2")
addSbtPlugin("com.github.sbt" % "sbt-unidoc" % "0.5.0")
addSbtPlugin("org.typelevel" % "sbt-typelevel" % "0.6.5")
addSbtPlugin("org.typelevel" % "sbt-typelevel-site" % "0.6.5")
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "2.3.0")
addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.12.2")
addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.1.2")
addSbtPlugin("com.github.sbt" % "sbt-ci-release" % "1.9.3")
addSbtPlugin("com.github.sbt" % "sbt-release" % "1.4.0")

if (sys.env.isDefinedAt("GITHUB_ACTION")) {
  Def.settings(
    addSbtPlugin("net.virtual-void" % "sbt-hackers-digest" % "0.1.2")
  )
} else {
  Nil
}