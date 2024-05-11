plugins {
  application
  alias(libs.plugins.graalVm)
  alias(libs.plugins.manifest)
  alias(libs.plugins.jlink)
  alias(libs.plugins.fatjar)
  alias(libs.plugins.docker)
  alias(libs.plugins.dockerJavaApplication)
}

val mainClassPath = "org.whatever.db.server.Application"
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
  implementation(projects.libs.api)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.vertxHazelcast)

  implementation(libs.mutiny)
  implementation(libs.mutinyVertx)

  annotationProcessor(libs.avajeInjectGenerator)

  implementation(projects.libs.core)

  implementation(libs.gestaltConfig)
  implementation(libs.gestaltToml)
  implementation(libs.gestaltYaml)
//  implementation(libs.gestaltKotlin)
  implementation(libs.gestaltJSON)
  implementation(libs.gestaltGit)

  implementation(projects.libs.heapBufferStore)

  testImplementation(libs.vertxJunit5)
}

docker {
  javaApplication {
    baseImage = "ghcr.io/graalvm/jdk-community:22"
    jvmArgs.addAll("-Xms256m", "-Xmx2048m")
  }
}

jlink {
  options.addAll("--strip-debug", "--compress", "2", "--no-header-files")
  enableCds()
  mainClass = mainClassPath
  moduleName = group.toString()
  addExtraDependencies(
    "io.vertx.clustermanager.hazelcast",
    "java.transaction.xa",
    "io.vertx"
  )
  launcher {
    name = "WhateverDB"
  }
  jpackage {
    skipInstaller = true
  }
  customImage {
    jdkModules.addAll(listOf("jdk.management.agent", "java.transaction.xa"))
  }
  mergedModule {
    requires("io.vertx.core")
  }
}

tasks.shadowJar {
  minimize()
  mergeServiceFiles()
}