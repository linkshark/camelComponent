package com.linkjb.camelcomponent.pool.demo3;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.Closeable;
import java.sql.Connection;

public class DbPool implements Closeable {
    private GenericObjectPool<Connection> internalPool;

    public DbPool(GenericObjectPoolConfig poolConfig, PooledObjectFactory factory) {
        if (this.internalPool != null) {
            try {
                closeInternalPool();
            } catch (Exception e) {
            }
        }
        this.internalPool = new GenericObjectPool(factory, poolConfig);
    }

    // 获取连接
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = internalPool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException("Could not get connection from the pool", e);
        }
        return connection;
    }

    // 返还连接
    public void returnConnection(Connection connection) {
        internalPool.returnObject(connection);
    }

    @Override
    public void close() {
        this.closeInternalPool();
    }

    private void closeInternalPool() {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new RuntimeException("Could not destroy the pool", e);
        }
    }
}
