module org.whatever.db.server.web {
  requires io.vertx.core;
  requires org.slf4j;
  requires jul.to.slf4j;
  requires org.slf4j.jdk.platform.logging;
  requires io.vertx.web;
  requires io.avaje.inject;
  requires static lombok;
  requires org.jetbrains.annotations;

  provides io.avaje.inject.spi.Module with org.whatever.server.web.WebModule;
}