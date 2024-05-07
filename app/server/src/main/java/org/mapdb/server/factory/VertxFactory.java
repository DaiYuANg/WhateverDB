package org.mapdb.server.factory;

import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetServerOptions;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import org.jetbrains.annotations.NotNull;

@Factory
public class VertxFactory {

  @Bean
  Vertx vertx() {
    return Vertx.builder().withClusterManager(new HazelcastClusterManager()).buildClustered()
      .toCompletionStage().toCompletableFuture().join();
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
