package org.whatever.db.core.ser;

import org.whatever.db.api.DataOutput2;

public interface StringArrayKeys {

  int commonPrefixLen();

  int length();

  int[] getOffset();

  StringArrayKeys deleteKey(int pos);

  StringArrayKeys copyOfRange(int from, int to);

  StringArrayKeys putKey(int pos, String newKey);

  int compare(int pos1, String string);

  int compare(int pos1, int pos2);

  String getKeyString(int pos);

  boolean hasUnicodeChars();

  void serialize(DataOutput2 out, int prefixLen);
}
