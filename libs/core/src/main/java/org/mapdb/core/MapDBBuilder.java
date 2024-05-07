package org.mapdb.core;

import org.jetbrains.annotations.NotNull;
import org.mapdb.core.store.HeapBufStore;
import org.mapdb.core.store.Store;

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
  public Mapdb make() {
    return new Mapdb(store);
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