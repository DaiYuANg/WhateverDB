plugins {
  id("java")
}

group = "org.whatever.store.heap.buffer.store"

dependencies {
  implementation(projects.libs.api)
  compileOnly(libs.autoService)
  annotationProcessor(libs.autoService)
}

