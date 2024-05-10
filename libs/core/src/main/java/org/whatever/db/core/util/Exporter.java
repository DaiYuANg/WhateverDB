package org.whatever.db.core.util;

import org.jetbrains.annotations.NotNull;
import org.whatever.db.api.DataOutput2ByteArray;

public interface Exporter {

    void exportToDataOutput2(@NotNull DataOutput2ByteArray output);
}
