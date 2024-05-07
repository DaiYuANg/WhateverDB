package org.mapdb.core.store.legacy;

public class Fun {
  public static long roundUp(long number, long roundUpToMultipleOf) {
    return ((number + roundUpToMultipleOf - 1) / (roundUpToMultipleOf)) * roundUpToMultipleOf;
  }

}
