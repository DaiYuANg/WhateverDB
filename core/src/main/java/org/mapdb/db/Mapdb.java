package org.mapdb.db;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.mapdb.store.HeapBufStore;
import org.mapdb.store.Store;

import java.io.File;

public class Mapdb {
  @Getter
  private final Store store;

  public Mapdb(Store store) {
    this.store = store;
  }

  public void close() {

  }

  public static class MapdbBuilder {

    private final Store store;

    private MapdbBuilder(Store store) {
      this.store = store;
    }

    @NotNull
    public static Mapdb.MapdbBuilder appendFile(@NotNull File f) {
      return null;
    }

    @NotNull
    public Mapdb make() {
      return new Mapdb(store);
    }

    @NotNull
    public static Mapdb.MapdbBuilder heapSer() {
      return new MapdbBuilder(new HeapBufStore());
    }

    @NotNull
    public static Mapdb.MapdbBuilder memoryDB() {
      return new MapdbBuilder(new HeapBufStore());
    }
  }
}
