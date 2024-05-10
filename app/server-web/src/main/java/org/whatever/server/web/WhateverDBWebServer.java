package org.whatever.server.web;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.whatever.server.web.context.DIContext;

import java.util.function.Consumer;

@Singleton
@Slf4j
public class WhateverDBWebServer {

  private final Router router;

  private final HttpServer httpServer;

  public WhateverDBWebServer(@NotNull Vertx vertx, Router router) {
    this.httpServer = vertx.createHttpServer();
    this.router = router;
    val messageRoute = router.get("/api/message"); // (1)
    messageRoute.handler(rc -> {
      rc.response().end("Hello React from Vert.x!"); // (2)
    });
    router.get().handler(StaticHandler.create()); // (3)
  }

  public void start() {
    httpServer
      .requestHandler(router)
      .listen(8080)
      .onSuccess(event -> log.atInfo().log("Server started on port 8080"));
  }

  public static void main(String[] args) {
    val server = DIContext.INSTANCE.getBeanScope().get(WhateverDBWebServer.class);
    server.start();
  }
}