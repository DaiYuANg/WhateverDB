plugins {
  alias(libs.plugins.jmh)
}

group = "org.whatever.db.core"

dependencies {
  implementation(libs.fastutil)
  implementation(libs.guava)
  compileOnly(libs.jetbrainsAnnotation)
  implementation(libs.eclipseCollections)
  implementation(libs.eclipseCollectionsAPI)
  implementation(libs.eclipseCollectionsForkjoin)
  implementation(projects.libs.api)
  implementation("org.lz4:lz4-java:1.8.0")
  implementation(libs.slf4j)
  implementation(libs.apacheCommonLang3)
  implementation(libs.apacheCommonCompress)
  implementation(libs.apacheCommonIO)
  implementation(libs.fury)
}
