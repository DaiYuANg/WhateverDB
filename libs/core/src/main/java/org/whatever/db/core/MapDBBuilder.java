package org.whatever.db.core;

import org.jetbrains.annotations.NotNull;
import org.whatever.db.core.store.HeapBufStore;
import org.whatever.db.api.Store;

import java.io.File;

public class MapDBBuilder {

  private final Store store;

  private MapDBBuilder(Store store) {
    this.store = store;
  }

  @NotNull
  public static MapDBBuilder appendFile(@NotNull File f) {
    return null;
  }

  @NotNull
  public WhateverDB make() {
    return new WhateverDB(store);
  }

  @NotNull
  public static MapDBBuilder heapSer() {
    return new MapDBBuilder(new HeapBufStore());
  }

  @NotNull
  public static MapDBBuilder memoryDB() {
    return new MapDBBuilder(new HeapBufStore());
  }
}