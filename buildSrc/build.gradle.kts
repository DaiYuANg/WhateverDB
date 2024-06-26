//buildscript {
//    ext.kotlin_version = '1.2.50'
//
//    repositories {
//        mavenCentral()
//    }
//
//    dependencies {
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
//    }
//}
//
//apply plugin: "kotlin"
//
//compileKotlin {
//    kotlinOptions {
////        freeCompilerArgs = ['-Xenable-jvm-default']
//        jvmTarget = '1.8'
//    }
//}
//
//
//sourceSets {
//    main.kotlin.srcDirs += 'src/main/java'
//    main.java.srcDirs += 'src/main/java'
//    test.kotlin.srcDirs += 'src/test/java'
//    test.java.srcDirs += 'src/test/java'
//
//
//}
//
//repositories {
//    mavenCentral()
//}
//
//
//dependencies {
//    compile "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
//}
//
//
//task codegenClean(type: Delete) {
//    delete "../srcGen"
//}
//
//task codegen(type:JavaExec) {
//
//    dependsOn(codegenClean)
//    main = "MDBCodeGen"
//    classpath = sourceSets.main.runtimeClasspath
//}
//
//
//build.dependsOn(codegen)

plugins{
    `kotlin-dsl`
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
}

dependencies{
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}