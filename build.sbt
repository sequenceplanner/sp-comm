import SPSettings._

lazy val projectName = "sp-comm"
lazy val projectVersion = "0.9.6-SNAPSHOT"

lazy val spDep = Def.setting(Seq(
  PublishingSettings.orgNameFull %%% "sp-domain" % "0.9.6-SNAPSHOT"
))

lazy val buildSettings = Seq(
  name         := projectName,
  description  := "Support functions for sp micro services",
  version      := projectVersion,
  libraryDependencies ++= domainDependencies.value,
  libraryDependencies ++= spDep.value,
  scmInfo := Some(ScmInfo(
    PublishingSettings.githubSP(projectName),
    PublishingSettings.githubscm(projectName)
    )
  )
)

lazy val root = project.in(file("."))
  .aggregate(spcomm_jvm, spcomm_js)
  .settings(defaultBuildSettings)
  .settings(buildSettings)
  .settings(
    publish              := {},
    publishLocal         := {},
    publishArtifact      := false,
    Keys.`package`       := file("")
    )


lazy val spcomm = (crossProject.crossType(CrossType.Full) in file("."))
  .settings(defaultBuildSettings)
  .settings(buildSettings)
  .jvmSettings(
    libraryDependencies ++= commDependencies.value
  )
  .jsSettings()

lazy val spcomm_jvm = spcomm.jvm
lazy val spcomm_js = spcomm.js
