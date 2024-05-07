package org.mapdb.server.context;

import io.avaje.inject.BeanScope;
import lombok.Getter;

@Getter
public enum DIContext {
  INSTANCE;

  private final BeanScope beanScope = BeanScope.builder().build();
}
