plugins {
  alias(libs.plugins.frontend)
  application
}

group = "org.whatever.server.web"

dependencies {
  implementation(libs.vertxWeb)
  implementation(libs.vertxCore)
  implementation(libs.avajeInject)
  implementation(libs.slf4j)
  implementation(libs.slf4jJdkPlatform)
  implementation(libs.slf4jJulBridage)
  implementation(libs.logback)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)
}

val mainClassPath = "org.whatever.server.web.WhateverDBWebServer"

val frontendDir = project.layout.projectDirectory.file("src/main/frontend").asFile

frontend {
  nodeVersion = "20.9.0"
  packageJsonDirectory.set(frontendDir)
  assembleScript.set("run build")
}

val projectDir = project.layout.projectDirectory.asFile
val buildDir: String = project.layout.buildDirectory.asFile.get().absolutePath

val copyToWebRoot by tasks.creating(Copy::class) {
  dependsOn(tasks.assembleFrontend)
  from("${projectDir.absolutePath}/src/main/frontend/dist")
  to(File("${buildDir}/classes/java/main/webroot"))
  destinationDir = File("${buildDir}/classes/java/main/webroot")
}

tasks.build {
  dependsOn(tasks.processResources)
}

tasks.jar{
  duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.processResources {
  dependsOn(copyToWebRoot)
}