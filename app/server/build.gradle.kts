plugins {
  application
  alias(libs.plugins.graalVm)
  alias(libs.plugins.manifest)
  alias(libs.plugins.jlink)
  alias(libs.plugins.fatjar)
}

val mainClassPath = "org.mapdb.server.Application"
application {
  applicationDefaultJvmArgs = devJvmArguments
  mainClass = mainClassPath
}

group = "org.mapdb.server"

dependencies {
  implementation(libs.vertxCore)
  implementation(libs.vertxKotlin)
  implementation(libs.avajeInject)
  implementation(libs.slf4j)
  implementation(libs.slf4jJulBridage)
  implementation(libs.slf4jJdkPlatform)
  implementation(libs.logback)
  implementation(libs.rxjava3)
  implementation(libs.picocli)
  implementation(libs.picocliJline)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.vertxHazelcast)

  implementation(libs.mutiny)
  implementation(libs.mutinyVertx)

  annotationProcessor(libs.avajeInjectGenerator)

  implementation(projects.libs.core)

  implementation(libs.gestaltToml)
  implementation(libs.gestaltYaml)
  implementation(libs.gestaltKotlin)
  implementation(libs.gestaltJSON)
  implementation(libs.gestaltConfig)
  implementation(libs.gestaltGit)

  testImplementation(libs.vertxJunit5)
}

