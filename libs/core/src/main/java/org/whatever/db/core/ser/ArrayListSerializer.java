package org.whatever.db.core.ser;

import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.whatever.db.api.DataInput2;
import org.whatever.db.api.DataOutput2;
import org.whatever.db.api.Serializer;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayListSerializer<E> implements Serializer<ArrayList<E>> {

  protected final Serializer<E> ser;

  public ArrayListSerializer(Serializer<E> ser) {
    this.ser = ser;
  }

  @Override
  public void serialize(@NotNull DataOutput2 out, @NotNull ArrayList<E> list) {
    out.writePackedInt(list.size());
    list.forEach(e -> ser.serialize(out, e));
  }

  @Override
  public ArrayList<E> deserialize(@NotNull DataInput2 input) {
    val size = input.readPackedInt();
    return IntStream.range(0, size)
      .mapToObj(i -> ser.deserialize(input))
      .collect(Collectors.toCollection(() -> new ArrayList<>(size)));
  }

  @Override
  public @Nullable Class<?> serializedType() {
    return ArrayListSerializer.class;
  }
}
