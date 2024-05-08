package org.mapdb.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.core.VertxOptions;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.core.net.NetServer;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import jakarta.inject.Named;
import org.jetbrains.annotations.NotNull;

@Factory
public class VertxFactory {

  @Bean
  @Named("vertx")
  Vertx vertx() {
    return Vertx.clusteredVertxAndAwait(new VertxOptions().setClusterManager(new HazelcastClusterManager()));
  }


  @Bean
  NetServer netServer(@NotNull Vertx vertx) {
    return vertx.createNetServer();
  }

  @Bean
  HttpServer httpServer(@NotNull Vertx vertx) {
    return vertx.createHttpServer();
  }
}
