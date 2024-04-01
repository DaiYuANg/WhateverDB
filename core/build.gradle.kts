plugins {
  alias(libs.plugins.jmh)
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
  implementation("org.lz4:lz4-java:1.8.0")
  implementation(libs.slf4j)
}
