package org.mapdb.server.verticle;

import io.avaje.inject.Component;
import io.avaje.inject.Prototype;
import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

@Component
@Prototype
@Slf4j
public class DBInstanceVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    log.atInfo().log("this :{} started",this);
  }
}
