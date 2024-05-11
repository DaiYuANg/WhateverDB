package org.whatever.db.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import lombok.SneakyThrows;
import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder;
import org.github.gestalt.config.source.EnvironmentConfigSourceBuilder;
import org.github.gestalt.config.source.MapConfigSourceBuilder;

@Factory
public class ConfigFactory {


  @SneakyThrows
  @Bean
  Gestalt gestalt() {
    Gestalt gestalt = new GestaltBuilder()
      .addSource(ClassPathConfigSourceBuilder.builder().setResource("/default.properties").build())  // Load the default property files from resources.
      .addSource(EnvironmentConfigSourceBuilder.builder().setPrefix("WHATEVER_DB").build())
      .build();
    gestalt.loadConfigs();
    return gestalt;
  }
}
