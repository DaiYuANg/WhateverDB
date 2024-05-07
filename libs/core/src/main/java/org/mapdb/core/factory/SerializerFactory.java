package org.mapdb.core.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import org.apache.fury.Fury;
import org.apache.fury.ThreadSafeFury;
import org.apache.fury.config.Language;

@Factory
public class SerializerFactory {

  @Bean
  ThreadSafeFury fury() {
    return Fury.builder().withLanguage(Language.JAVA)
      .requireClassRegistration(true)
      .buildThreadSafeFuryPool(1, Runtime.getRuntime().availableProcessors());
  }
}
