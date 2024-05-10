package org.whatever.store;

import com.google.auto.service.AutoService;
import org.jetbrains.annotations.NotNull;
import org.whatever.db.api.Serializer;
import org.whatever.db.api.Store;

@AutoService(Store.class)
public class HeapBufferStore implements Store {
  @Override
  public long preallocate() {
    return 0;
  }

  @Override
  public <R> void preallocatePut(long recid, @NotNull Serializer<R> serializer, @NotNull R record) {

  }

  @Override
  public <R> long put(@NotNull R record, @NotNull Serializer<R> serializer) {
    return 0;
  }

  @Override
  public <R> void update(long recid, @NotNull Serializer<R> serializer, @NotNull R updatedRecord) {

  }

  @Override
  public void verify() {

  }

  @Override
  public void commit() {

  }

  @Override
  public void compact() {

  }

  @Override
  public boolean isThreadSafe() {
    return false;
  }

  @Override
  public <R> void updateAtomic(long recid, @NotNull Serializer<R> serializer, @NotNull Transform<R> r) {

  }

  @Override
  public <R> boolean compareAndUpdate(long recid, @NotNull Serializer<R> serializer, @NotNull R expectedOldRecord, @NotNull R updatedRecord) {
    return false;
  }

  @Override
  public <R> boolean compareAndDelete(long recid, @NotNull Serializer<R> serializer, @NotNull R expectedOldRecord) {
    return false;
  }

  @Override
  public <R> void delete(long recid, @NotNull Serializer<R> serializer) {

  }

  @Override
  public <R> @NotNull R getAndDelete(long recid, @NotNull Serializer<R> serializer) {
    return null;
  }

  @Override
  public <K> @NotNull K get(long recid, @NotNull Serializer<K> ser) {
    return null;
  }

  @Override
  public void close() {

  }

  @Override
  public void getAll(@NotNull GetAllCallback callback) {

  }

  @Override
  public boolean isEmpty() {
    return false;
  }
}