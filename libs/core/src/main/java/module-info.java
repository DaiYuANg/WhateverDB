module org.mapdb.core {
  requires org.slf4j;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires org.eclipse.collections.api;
  requires org.eclipse.collections.impl;
  requires org.lz4.java;
  requires com.google.common;
  requires io.avaje.inject;
  requires kotlin.stdlib;
  requires org.apache.fury.core;
  requires java.sql;

  exports org.mapdb.core.store;
  exports org.mapdb.core.exception;
  exports org.mapdb.core.config;
  exports org.mapdb.core;
  exports org.mapdb.core.ser;

  provides io.avaje.inject.spi.Module with org.mapdb.core.factory.FactoryModule;
}