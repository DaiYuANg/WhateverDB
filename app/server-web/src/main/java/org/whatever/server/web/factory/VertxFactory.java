package org.whatever.server.web.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import jakarta.inject.Named;
import org.jetbrains.annotations.NotNull;

@Factory
public class VertxFactory {
  @Bean
  @Named("vertx")
  Vertx vertx() {
    return Vertx.vertx();
  }

  @Bean
  Router router(@NotNull Vertx vertx) {
    return Router.router(vertx);
  }
}
