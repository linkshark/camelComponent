package com.linkjb.camelcomponent.pool.demo2pool;

/**
 * @ClassName StringPoolFac
 * @Description TODO
 * @Author shark
 * @Data 2022/8/23 14:30
 **/

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * 字符串池工厂
 */
public class StringPoolFac extends BasePooledObjectFactory<String> {
    public StringPoolFac() {
        super();
    }

    @Override
    public String create() throws Exception {
        return "str-val-";
    }

    @Override
    public PooledObject<String> wrap(String s) {
        return new DefaultPooledObject<>(s);
    }

    @Override
    public void destroyObject(PooledObject<String> p) throws Exception {
    }

    @Override
    public boolean validateObject(PooledObject<String> p) {
        return super.validateObject(p);
    }

    @Override
    public void activateObject(PooledObject<String> p) throws Exception {
        super.activateObject(p);
    }

    @Override
    public void passivateObject(PooledObject<String> p) throws Exception {
        super.passivateObject(p);
    }
}

