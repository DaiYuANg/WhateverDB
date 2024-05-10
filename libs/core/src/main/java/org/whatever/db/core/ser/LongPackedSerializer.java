package org.whatever.db.core.ser;

import org.whatever.db.api.DataInput2;
import org.whatever.db.api.DataOutput2;

import java.util.Comparator;

/**
 * Created by jan on 2/28/16.
 */
public class LongPackedSerializer extends LongSerializer {
  @Override
  public void serialize(DataOutput2 out, Long value) {
    out.writePackedLong(value);
  }

  @Override
  public Long deserialize(DataInput2 in, int available) {
    return in.readPackedRecid();
  }

  @Override
  public void valueArraySerialize(DataOutput2 out, long[] vals) {
    for (long o : vals) {
      out.writePackedLong(o);
    }
  }

  @Override
  public long[] valueArrayDeserialize(DataInput2 in, int size) {
    long[] ret = new long[size];
    throw new UnsupportedOperationException();
//        in.unpackLongArray(ret, 0, size);
//        return ret;
  }

  @Override
  public int fixedSize() {
    return -1;
  }

  @Override
  public int valueArrayBinarySearch(Long key, DataInput2 input, int keysLen, Comparator comparator) {
    if (comparator != this)
      return super.valueArrayBinarySearch(key, input, keysLen, comparator);
    long key2 = key;
    for (int pos = 0; pos < keysLen; pos++) {
      long from = input.readPackedLong();

      if (key2 <= from) {
        input.unpackLongSkip(keysLen - pos - 1);
        return (key2 == from) ? pos : -(pos + 1);
      }
    }

    //not found
    return -(keysLen + 1);
  }

  @Override
  public Long valueArrayBinaryGet(DataInput2 input, int keysLen, int pos) {
    input.unpackLongSkip(pos);
    return input.readPackedLong();
  }


}
