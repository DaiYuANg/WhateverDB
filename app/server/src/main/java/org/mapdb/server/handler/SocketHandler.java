package org.mapdb.server.handler;

import io.avaje.inject.Component;
import io.vertx.core.Handler;
import io.vertx.core.net.NetSocket;

@Component
public class SocketHandler implements Handler<NetSocket> {
  @Override
  public void handle(NetSocket netSocket) {

  }
}
