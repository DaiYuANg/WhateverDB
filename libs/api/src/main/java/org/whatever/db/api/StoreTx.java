package org.whatever.db.api;

public interface StoreTx extends Store {
    void commit();
    void rollback();

}
