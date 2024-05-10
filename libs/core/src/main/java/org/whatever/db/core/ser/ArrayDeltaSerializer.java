package org.whatever.db.core.ser;

import org.whatever.db.api.DataInput2;
import org.whatever.db.api.DataOutput2;
import org.whatever.db.api.Serializer;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by jan on 2/28/16.
 */
public class ArrayDeltaSerializer<T> extends ArraySerializer<T> implements Serializable {

  @Serial
  private static final long serialVersionUID = -930920902390439234L;


  public ArrayDeltaSerializer(Serializer<T> serializer) {
    super(serializer);
  }

  @Override
  public int valueArraySearch(Object[] keys, T[] key, Comparator<?> comparator) {
    return 0;
  }

  @Override
  public void valueArraySerialize(DataOutput2 out, Object[] vals2) {
    if (vals2.length == 0)
      return;
    //write first array
    Object[] prevKey = (Object[]) ((Object[]) vals2)[0];
    out.packInt(prevKey.length);
    for (Object key : prevKey) {
      serializer.serialize(out, (T) key);
    }

    //write remaining arrays
    for (int i = 1; i < ((Object[]) vals2).length; i++) {
      Object[] key = (Object[]) ((Object[]) vals2)[i];
      //calculate number of entries equal with prevKey
      int len = Math.min(key.length, prevKey.length);
      int pos = 0;
      while (pos < len && (key[pos] == prevKey[pos] || serializer.equals((T) key[pos], (T) prevKey[pos]))) {
        pos++;
      }
      out.packInt(pos);
      //write remaining bytes
      out.packInt(key.length - pos);
      for (; pos < key.length; pos++) {
        serializer.serialize(out, (T) key[pos]);
      }
      prevKey = key;
    }

  }

  @Override
  public Object[] valueArrayDeserialize(DataInput2 in, final int size) {
    Object[] ret = new Object[size];
    if (size == 0)
      return ret;
    int ss = in.unpackInt();
    Object[] prevKey = new Object[ss];
    for (int i = 0; i < ss; i++) {
      prevKey[i] = serializer.deserialize(in, -1);
    }
    ret[0] = prevKey;
    for (int i = 1; i < size; i++) {
      //number of items shared with prev
      int shared = in.unpackInt();
      //number of items unique to this array
      int unq = in.unpackInt();
      Object[] key = new Object[shared + unq];
      //copy items from prev array
      System.arraycopy(prevKey, 0, key, 0, shared);
      //and read rest
      for (; shared < key.length; shared++) {
        key[shared] = serializer.deserialize(in, -1);
      }
      ret[i] = key;
      prevKey = key;
    }
    return ret;
  }

}
