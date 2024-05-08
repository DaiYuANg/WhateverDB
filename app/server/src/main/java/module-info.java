module org.mapdb.server {
  requires io.avaje.inject;
  requires io.vertx.core;
  requires io.vertx.kotlin;
  requires static lombok;
  requires org.slf4j;
  requires org.jetbrains.annotations;
  requires info.picocli;
  requires io.reactivex.rxjava3;
  requires org.mapdb.core;
  requires org.github.gestalt.core;
  requires org.github.gestalt.yaml;
  requires io.vertx.clustermanager.hazelcast;
  requires java.transaction.xa;
  requires org.slf4j.jdk.platform.logging;
  requires jul.to.slf4j;
  requires io.smallrye.mutiny.vertx.core;
  requires io.smallrye.mutiny;

  opens org.mapdb.server.command to info.picocli;
  exports org.mapdb.server to info.picocli;
  exports org.mapdb.server.factory to info.picocli;
  exports org.mapdb.server.context to info.picocli;
  exports org.mapdb.server.provider to info.picocli;

  provides io.avaje.inject.spi.Module with org.mapdb.server.ServerModule;
}