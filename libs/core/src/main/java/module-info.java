module org.whatever.db.core {
  requires org.slf4j;
  requires static lombok;
  requires static org.jetbrains.annotations;
  requires org.eclipse.collections.api;
  requires org.eclipse.collections.impl;
  requires org.lz4.java;
  requires com.google.common;
  requires org.apache.fury.core;
  requires java.sql;
  requires org.whatever.db.api;

  exports org.whatever.db.core.store;
  exports org.whatever.db.core.config;
  exports org.whatever.db.core;
  exports org.whatever.db.core.ser;
}