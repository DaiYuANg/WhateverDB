package org.whatever.db.core.ser;

import org.jetbrains.annotations.NotNull;
import org.whatever.db.api.DataIO;
import org.whatever.db.api.DataInput2;
import org.whatever.db.api.DataOutput2;

/**
 * Created by jan on 2/28/16.
 */
public class StringOrigHashSerializer extends StringSerializer {
    @Override
    public void serialize(DataOutput2 out, String value) {
        out.writeUTF(value);
    }

    @Override
    public String deserialize(DataInput2 in, int available) {
        return in.readUTF();
    }

    @Override
    public boolean isTrusted() {
        return true;
    }


//        @Override
//        public BTreeKeySerializer getBTreeKeySerializer(Comparator comparator) {
//            if(comparator!=null && comparator!=Fun.COMPARATOR) {
//                return super.getBTreeKeySerializer(comparator);
//            }
//            return BTreeKeySerializer.STRING;
//        }


    @Override
    public int hashCode(@NotNull String s, int seed) {
        return DataIO.intHash(s.hashCode() + seed);
    }
}
