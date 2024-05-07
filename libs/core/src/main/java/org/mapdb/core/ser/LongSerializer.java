package org.mapdb.core.ser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mapdb.core.io.DataInput2;
import org.mapdb.core.io.DataOutput2;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by jan on 2/28/16.
 */
public class LongSerializer extends EightByteSerializer<Long> {

  @Override
  public void serialize(DataOutput2 out, Long value) {
    out.writeLong(value);
  }

  @Override
  public Long deserialize(@NotNull DataInput2 in) {
    return in.readLong();
  }

  @Nullable
  @Override
  public Class serializedType() {
    return long[].class;
  }


  @Override
  protected Long unpack(long l) {
    return l;
  }

  @Override
  protected long pack(@NotNull Long l) {
    return l;
  }

  @Override
  public int valueArraySearch(long[] keys, Long key) {
    return Arrays.binarySearch((long[]) keys, key);
  }


  @Override
  public int valueArrayBinarySearch(Long key, DataInput2 input, int keysLen, Comparator comparator) {
    if (comparator != this)
      return super.valueArrayBinarySearch(key, input, keysLen, comparator);
    long key2 = key;
    for (int pos = 0; pos < keysLen; pos++) {
      long from = input.readLong();

      if (key2 <= from) {
        input.skipBytes((keysLen - pos - 1) * 8);
        return (key2 == from) ? pos : -(pos + 1);
      }
    }

    //not found
    return -(keysLen + 1);
  }

}
