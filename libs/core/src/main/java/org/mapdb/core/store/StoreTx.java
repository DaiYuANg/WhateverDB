package org.mapdb.core.store;

public interface StoreTx extends Store {
    void commit();
    void rollback();

}
