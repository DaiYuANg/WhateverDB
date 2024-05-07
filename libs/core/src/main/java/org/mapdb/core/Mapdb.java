package org.mapdb.core;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.mapdb.core.store.HeapBufStore;
import org.mapdb.core.store.Store;

import java.io.File;

@Getter
public class Mapdb {
  private final Store store;

  public Mapdb(Store store) {
    this.store = store;
  }

  public void close() {

  }
}
