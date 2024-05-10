package org.whatever.db.core.ser;

import org.jetbrains.annotations.Nullable;
import org.whatever.db.api.DataInput2;
import org.whatever.db.api.DataOutput2;

import java.math.BigInteger;

/**
 * Created by jan on 2/28/16.
 */
public class BigIntegerSerializer extends DefaultGroupSerializer<BigInteger> {
    @Override
    public void serialize(DataOutput2 out, BigInteger value) {
        Serializers.BYTE_ARRAY.serialize(out, value.toByteArray());
    }

    @Override
    public BigInteger deserialize(DataInput2 in) {
        return new BigInteger(Serializers.BYTE_ARRAY.deserialize(in));
    }

    @Nullable
    @Override
    public Class serializedType() {
        return BigInteger.class;
    }

    @Override
    public boolean isTrusted() {
        return true;
    }
}
