plugins{
  alias(libs.plugins.graalVm)
}
dependencies {
  implementation(libs.picocli)
  implementation(libs.picocliJline)
  annotationProcessor(libs.picocliCodegen)
  implementation(projects.libs.core)
  implementation(libs.slf4j)
  implementation(libs.logback)
  implementation(libs.jansi)
}
