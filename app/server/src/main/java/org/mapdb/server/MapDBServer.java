package org.mapdb.server;

import io.avaje.inject.Component;
import io.avaje.inject.PostConstruct;
import io.vertx.core.AbstractVerticle;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.core.net.NetServer;
import io.vertx.mutiny.core.net.NetSocket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Set;
import java.util.function.Consumer;

@Component
@RequiredArgsConstructor
@Slf4j
public class MapDBServer {

  private final NetServer netServer;

  private final Vertx vertx;

  private final Set<AbstractVerticle> verticles;

  @PostConstruct
  public void init() {
    verticles.forEach(vertx::deployVerticleAndForget);
    netServer.connectHandler(netSocket -> {

    });
  }

  public void startServer() {
    val listen = netServer.listenAndAwait();
    log.info("Listen Port:{}", listen.actualPort());
  }
}