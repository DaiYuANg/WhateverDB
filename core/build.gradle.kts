plugins {
  id("java")
}

group = "org.mapdb.core"
version = "unspecified"

repositories {
  mavenCentral()
}

dependencies {
  implementation(libs.guava)
  compileOnly(libs.jetbrainsAnnotation)
  implementation(libs.eclipseCollections)
  implementation(libs.eclipseCollectionsAPI)
  implementation(libs.eclipseCollectionsForkjoin)
  implementation(libs.avajeInject)
  annotationProcessor(libs.avajeInjectGenerator)
  testImplementation(libs.avajeInjectTest)

  implementation(libs.slf4j)
}
