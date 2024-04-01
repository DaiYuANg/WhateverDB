import name.remal.gradle_plugins.lombok.LombokPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

plugins {
  `java-library`
  kotlin("jvm") version libs.versions.kotlin
  alias(libs.plugins.lombok) apply false
  alias(libs.plugins.versionCheck)
}


val jdkVersion = libs.versions.jdkVersion.get()

val rlibs = rootProject.libs

allprojects {
  repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
  }
}

subprojects {
  apply<JavaLibraryPlugin>()
  apply<KotlinPluginWrapper>()
  apply<LombokPlugin>()
  dependencies {
    dependencies {
      testImplementation(enforcedPlatform(rlibs.junitBom))
      testImplementation(rlibs.junitEngine)
      testImplementation(rlibs.junitPerf)
      testImplementation(rlibs.junitJuiter)
      testImplementation(rlibs.junitApi)
      testImplementation(rlibs.dataFaker)
    }
    tasks.test {
      useJUnitPlatform()
    }

    java {
      sourceCompatibility = JavaVersion.toVersion(jdkVersion)
      targetCompatibility = JavaVersion.toVersion(jdkVersion)
    }

    kotlin {
      jvmToolchain(jdkVersion.toInt())
    }
  }
}
