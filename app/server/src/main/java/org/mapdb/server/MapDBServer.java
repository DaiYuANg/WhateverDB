package org.mapdb.server;

import io.avaje.inject.Component;
import io.avaje.inject.PostConstruct;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapdb.server.handler.SocketHandler;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class MapDBServer {

  private final NetServer netServer;

  private final Vertx vertx;

  private final SocketHandler socketHandler;

  private final Set<AbstractVerticle> verticles;

  @PostConstruct
  public void init() {
    verticles.forEach(vertx::deployVerticle);
    netServer.connectHandler(socketHandler);
  }

  public void startServer() {
    netServer.listen().onComplete(result -> {
      if (result.succeeded()) {
        log.atInfo().log("Server startup:{}", result.succeeded());
      }
    });
  }
}