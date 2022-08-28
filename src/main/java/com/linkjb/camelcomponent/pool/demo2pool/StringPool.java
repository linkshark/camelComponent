package com.linkjb.camelcomponent.pool.demo2pool;

/**
 * @ClassName StringPool
 * @Description TODO
 * @Author shark
 * @Data 2022/8/23 14:30
 **/

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 字符串池
 */
public class StringPool extends GenericObjectPool<String> {
    public StringPool(PooledObjectFactory<String> factory) {
        super(factory);
    }

    public StringPool(PooledObjectFactory<String> factory, GenericObjectPoolConfig<String> config) {
        super(factory, config);
    }
}

