package org.mapdb.server.factory;

import com.hazelcast.config.Config;
import io.avaje.inject.Bean;
import io.avaje.inject.Factory;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.http.HttpServer;
import io.vertx.mutiny.core.net.NetServer;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import jakarta.inject.Named;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@Factory
public class VertxFactory {

  @Bean
  ClusterManager hazelcastClusterManager() {
    val config = new Config();
    config.setProperty("hazelcast.logging.type", "slf4j");
    return new HazelcastClusterManager(config);
  }

  @Bean
  @Named("vertx")
  Vertx vertx(ClusterManager clusterManager) {
    return Vertx.clusteredVertxAndAwait(new VertxOptions().setClusterManager(clusterManager));
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
