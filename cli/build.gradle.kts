dependencies {
  implementation(libs.picocli)
  implementation(libs.picocliJline)
  annotationProcessor(libs.picocliCodegen)
  implementation(libs.slf4j)
  implementation(projects.core)
  implementation(libs.logback)
}
