package org.whatever.db.core;

import lombok.Getter;
import org.whatever.db.api.Store;

@Getter
public class WhateverDB {
  private final Store store;

  public WhateverDB(Store store) {
    this.store = store;
  }

  public void close() {

  }
}
