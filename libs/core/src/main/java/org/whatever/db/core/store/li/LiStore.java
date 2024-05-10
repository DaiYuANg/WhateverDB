package org.whatever.db.core.store.li;

import org.jetbrains.annotations.NotNull;
import org.whatever.db.api.exception.DBException;
import org.whatever.db.api.Store;
import org.whatever.db.api.DataInput2;
import org.whatever.db.api.DataInput2ByteBuffer;
import org.whatever.db.api.DataOutput2;
import org.whatever.db.api.DataOutput2ByteArray;
import org.whatever.db.api.Serializer;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;

public class LiStore implements Store {

    private final static int PAGE_SIZE = 1024;

    private final long[] index = new long[100_000];

    private final Queue<Integer> freeRecids = new LinkedList<>();
    private int recidTail = 1;

    private final Queue<Long> freePages = new LinkedList<>();
    private long pageTail = PAGE_SIZE;

    private final ByteBuffer data = ByteBuffer.allocate(64*1024*1024);



    @Override
    public long preallocate() {
        int recid = allocRecid();
        index[recid] = LiUtil.composeRecordType(LiUtil.R_PREALLOC);
        return recid;
    }

    @Override
    public <R> void preallocatePut(long recid, @NotNull Serializer<R> serializer, @NotNull R record) {
        long indexVal = index[(int) recid];
        if(indexVal == LiUtil.R_VOID)
            throw new DBException.RecordNotPreallocated();
        int recType = LiUtil.decompIndexValType(indexVal);
        if(recType != LiUtil.R_PREALLOC)
            throw new DBException.RecordNotPreallocated();

        long page = allocPage();
        int size = serializeToPage(record, serializer, (int) page);
        index[(int) recid] = LiUtil.composeIndexValSmall(size, page);
    }

    @Override
    public <R> @NotNull long put(@NotNull R record, @NotNull Serializer<R> serializer) {
        long page = allocPage();
        int size = serializeToPage(record, serializer, (int) page);

        int recid = allocRecid();

        index[recid] = LiUtil.composeIndexValSmall(size, page);
        return recid;
    }


    protected <R> int serializeToPage(@NotNull R record, @NotNull Serializer<R> serializer, long page) {
        DataOutput2 out = new DataOutput2ByteArray();
        serializer.serialize(out, record);
        byte[] b = out.copyBytes();
        if(b.length>PAGE_SIZE)
            throw new RuntimeException();

        ByteBuffer bb = data.duplicate();
        bb.position((int) page);
        bb.put(b);
        return b.length;
    }

    private int allocRecid() {
        Integer recid = freeRecids.poll();
        if(recid == null)
            return recidTail++;
        return recid;
    }

    private long allocPage() {
        Long ret = freePages.poll();
        if(ret==null) {
            ret = pageTail;
            pageTail+=PAGE_SIZE;
        }
        return ret;
    }

    @Override
    public <R> void update(long recid, @NotNull Serializer<R> serializer, @NotNull R updatedRecord) {
        long indexVal = index[(int) recid];
        if(indexVal== LiUtil.R_VOID)
            throw new DBException.RecordNotFound();
        int recType = LiUtil.decompIndexValType(indexVal);
        if(recType == LiUtil.R_PREALLOC)
            throw new DBException.PreallocRecordAccess();
        int size = LiUtil.decompIndexValSize(indexVal);
        long page = LiUtil.decompIndexValPage(indexVal);


        int newSize = serializeToPage(updatedRecord, serializer, page);
        index[(int) recid] = LiUtil.composeIndexValSmall(newSize, page);
    }

    @Override
    public void verify() {

    }

    @Override
    public void commit() {

    }

    @Override
    public void compact() {

    }

    @Override
    public boolean isThreadSafe() {
        return false;
    }

    @Override
    public <R> void updateAtomic(long recid, @NotNull Serializer<R> serializer, @NotNull Transform<R> r) {
        R rec = get(recid, serializer);
        rec = r.transform(rec);
        update(recid, serializer, rec);
    }

    @Override
    public <R> boolean compareAndUpdate(long recid, @NotNull Serializer<R> serializer, @NotNull R expectedOldRecord, @NotNull R updatedRecord) {
        R r = get(recid, serializer);
        if(!serializer.equals(r,expectedOldRecord))
            return false;
        update(recid, serializer, updatedRecord);
        return true;
    }

    @Override
    public <R> boolean compareAndDelete(long recid, @NotNull Serializer<R> serializer, @NotNull R expectedOldRecord) {
        R r = get(recid, serializer);
        if(!serializer.equals(r,expectedOldRecord))
            return false;
        delete(recid, serializer);
        return true;
    }

    @Override
    public <R> void delete(long recid, @NotNull Serializer<R> serializer) {
        long indexVal = index[(int) recid];
        if(indexVal== LiUtil.R_VOID)
            throw new DBException.RecordNotFound();
        int recType = LiUtil.decompIndexValType(indexVal);
        if(recType == LiUtil.R_PREALLOC)
            throw new DBException.PreallocRecordAccess();
        int size = LiUtil.decompIndexValSize(indexVal);
        long page = LiUtil.decompIndexValPage(indexVal);

        index[(int) recid] = LiUtil.R_VOID;
        freeRecids.add((int) recid);
        LiUtil.zeroOut(data, page, PAGE_SIZE);
        freePages.add(page);
    }

    @Override
    public <R> @NotNull R getAndDelete(long recid, @NotNull Serializer<R> serializer) {
        R r = get(recid, serializer);
        delete(recid, serializer);
        return r;
    }

    @Override
    public <K> @NotNull K get(long recid, @NotNull Serializer<K> ser) {
        long indexVal = index[(int) recid];
        if(indexVal== LiUtil.R_VOID)
            throw new DBException.RecordNotFound();
        int recType = LiUtil.decompIndexValType(indexVal);
        if(recType == LiUtil.R_PREALLOC)
            throw new DBException.PreallocRecordAccess();
        int size = LiUtil.decompIndexValSize(indexVal);
        long page = LiUtil.decompIndexValPage(indexVal);

        ByteBuffer bb = data.duplicate();
        bb.position((int) page);
        bb.limit((int) (page+size));
        DataInput2 input = new DataInput2ByteBuffer(bb);
        return ser.deserialize(input);
    }

    @Override
    public void close() {

    }

    @Override
    public void getAll(@NotNull GetAllCallback callback) {
        ByteBuffer bb = data.duplicate();
        for(int recid = 1; recid<recidTail; recid++){
            long indexVal = index[recid];
            if(indexVal== LiUtil.R_VOID)
                continue;
            int recType = LiUtil.decompIndexValType(indexVal);
            if(recType == LiUtil.R_PREALLOC)
                continue;
            int size = LiUtil.decompIndexValSize(indexVal);
            long page = LiUtil.decompIndexValPage(indexVal);
            byte[] b = new  byte[size];
            bb.position((int) page);
            bb.get(b);
            callback.takeOne(recid, b);
        }

    }

    @Override
    public boolean isEmpty() {
        return freeRecids.size() == recidTail-1;
    }

    @Override
    public int maxRecordSize() {
        return PAGE_SIZE;
    }
}
