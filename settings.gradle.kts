pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        google()
        maven { setUrl("https://jitpack.io") }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

plugins {
    id("com.gradle.enterprise") version "3.13.4"
    id("org.danilopianini.gradle-pre-commit-git-hooks") version "2.0.2"
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

buildCache {
    local {
        isEnabled = true
        directory = File(rootProject.projectDir, ".gradle/build-cache")
    }
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}
rootProject.name = "WhateverDB"
include("libs:core")
include("libs:common")
include("app:cli")
include("app:server")

include("website")
include("libs:api")
findProject(":libs:api")?.name = "api"
include("libs:heap-buffer-store")
findProject(":libs:heap-buffer-store")?.name = "heap-buffer-store"
include("app:server-web")
findProject(":app:server-web")?.name = "server-web"
