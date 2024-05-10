package org.whatever.db.core.ser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.whatever.db.api.DataInput2;
import org.whatever.db.api.DataOutput2;
import org.whatever.db.api.Serializer;

public class StringNoSizeSerializer implements Serializer<String> {

    @Override
    public void serialize(@NotNull DataOutput2 out, @NotNull String s) {
        out.writeUTF(s);
    }

    @Override
    public String deserialize(@NotNull DataInput2 input) {
        return input.readUTF();
    }

    @Override
    public @Nullable Class serializedType() {
        return String.class;
    }
}
