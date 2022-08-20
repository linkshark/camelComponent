package com.linkjb.camelcomponent.datasourcepool;

/**
 * @ClassName PoolEntry
 * @Description TODO
 * @Author shark
 * @Data 2022/8/11 17:40
 **/
import java.sql.Connection;

public class PoolEntry {

    private Connection conn;
    private long useStartTime; //开始使用时间

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public long getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(long useStartTime) {
        this.useStartTime = useStartTime;
    }

    public PoolEntry(Connection conn, long useStartTime) {
        super();
        this.conn = conn;
        this.useStartTime = useStartTime;
    }
}

