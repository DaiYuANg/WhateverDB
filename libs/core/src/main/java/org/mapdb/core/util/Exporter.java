package org.mapdb.core.util;

import org.jetbrains.annotations.NotNull;
import org.mapdb.core.io.DataOutput2ByteArray;

public interface Exporter {

    void exportToDataOutput2(@NotNull DataOutput2ByteArray output);
}
