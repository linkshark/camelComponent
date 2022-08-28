package com.linkjb.camelcomponent.pool.demo3;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class DbPoolConfig extends GenericObjectPoolConfig {

    public DbPoolConfig() {
        setMaxTotal(10);
        setMaxIdle(5);
        setMinIdle(2);
    }
}
