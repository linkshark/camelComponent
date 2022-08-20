package com.linkjb.camelcomponent.datasourcepool;

/**
 * @ClassName IConnectionPool
 * @Description 连接池接口
 * @Author shark
 * @Data 2022/8/11 17:38
 **/
import java.sql.Connection;

public interface IConnectionPool {

    /**
     * 获取Connection 复用机制
     */
    Connection getConn();

    /**
     *释放连接(可回收机制)
     */
    void release(Connection conn);
}
