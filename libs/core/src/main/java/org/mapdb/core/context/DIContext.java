package org.mapdb.core.context;

import io.avaje.inject.Bean;
import io.avaje.inject.BeanScope;
import lombok.Getter;

@Getter
public enum DIContext {
  INSTANCE;
  private final BeanScope beanScope = BeanScope.builder().build();
}
