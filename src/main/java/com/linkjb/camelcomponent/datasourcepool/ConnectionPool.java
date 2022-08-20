package com.linkjb.camelcomponent.datasourcepool;

/**
 * @ClassName ConnectionPool
 * @Description TODO
 * @Author shark
 * @Data 2022/8/11 17:40
 **/

import com.linkjb.camelcomponent.datasourcepool.config.DataSourceConfig;
import javafx.concurrent.Worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPool implements IConnectionPool {

    // 加载配置类
    DataSourceConfig config;

    // 写一个参数，用来标记当前有多少个活跃的连接(总的连接数)
    private AtomicInteger currentActive = new AtomicInteger(0);

    // 创建一个集合，干嘛的呢？用来存放连接，毕竟我们刚刚初始化的时候就需要创建initSize个连接
    // 并且，当我们释放连接的时候，我们就把连接放到这里面
    Vector<Connection> freePools = new Vector<Connection>();

    // 正在使用的连接池
    Vector<PoolEntry> usePools = new Vector<PoolEntry>();

    public ConnectionPool(DataSourceConfig config) {
        this.config = config;
        init();
    }

    // 初始化方法
    private void init() {
        try {
            // 我们的jdbc是不是每次都要加载呢？肯定不是的，只要加载一次就够了
            Class.forName(config.getDriver());
            for (int i = 0; i < Integer.valueOf(config.getInitSize()); i++) {
                Connection conn = createConn();
                freePools.add(conn);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //开启任务检查
        check();
    }

    // 定时检查占用时间超长的连接，并关闭
    private void check() {
        if (Boolean.valueOf(config.getHealth())) {
            Worker worker = new Worker();
            /**
             * #检查延迟时间
             * jdbc.delay=2000
             * #间隔时间，重复获得连接的频率
             * jdbc.period=2000
             */
            new Timer().schedule(worker, Long.valueOf(config.getDelay()), Long.valueOf(config.getPeriod()));
        }
    }


    class Worker extends TimerTask {
        public void run() {
            System.out.println("例行检查...");
            for (int i = 0; i < usePools.size(); i++) {
                PoolEntry entry = usePools.get(i);
                long startTime = entry.getUseStartTime();
                long currentTime = System.currentTimeMillis();
                try {
                    // # 连接超时时间，10S
                    if ((currentTime - startTime) > Long.valueOf(config.getTimeout())) {
                        Connection conn = entry.getConn();
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                            usePools.remove(i);
                            currentActive.decrementAndGet();
                            System.out.println("发现有超时连接强行关闭," + conn + ",空闲连接数：" + freePools.size() + "," + "在使用连接数:" + usePools.size() + ",总的连接数:" + currentActive.get());
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }
    }

    public synchronized Connection createConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            currentActive.incrementAndGet();
            System.out.println("new一个新的连接：" + conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 创建连接有了，是不是也应该获取连接呢？
     * @return
     */
    public synchronized Connection getConn() {
        Connection conn = null;
        //如果空闲连接池中不为空，获取一个连接出来
        if (!freePools.isEmpty()) {
            conn = freePools.get(0);
            freePools.remove(0);
        } else {
            if (currentActive.get() < Integer.valueOf(config.getMaxSize())) {
                //如果空闲连接为空，
                conn = createConn();
            } else {
                try {
                    //如果总连接数超过了连接总数，需要等待
                    System.out.println(Thread.currentThread().getName() + ",连接池最大连接数为:" + config.getMaxSize() + "已经满了，需要等待...");
                    wait(Integer.valueOf(config.getWaittime()));
                    return getConn();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }
        PoolEntry poolEntry = new PoolEntry(conn, System.currentTimeMillis());
        // 获取连接干嘛的？不就是使用的吗？所以，每获取一个，就放入正在使用池中
        usePools.add(poolEntry);
        System.out.println(Thread.currentThread().getName() + ",获取并使用连接:" + conn + ",空闲连接数：" + freePools.size() + "," + "再使用连接数:" + usePools.size() + ",总的连接数:" + currentActive.get());
        return poolEntry.getConn();
    }

    /**
     * 创建连接，获取连接都已经有了，接下来就是该释放连接了
     *
     * @param conn
     */
    public synchronized void release(Connection conn) {
        try {
            if (!conn.isClosed() && conn != null) {
                freePools.add(conn);
            }
            for (int i = 0; i < usePools.size(); i++) {
                if (usePools.get(i).getConn() == conn) {
                    usePools.remove(i);
                }
            }
            System.out.println("回收了一个连接:" + conn + ",空闲连接数为:" + freePools.size() + ",在用连接数为:" + usePools.size());
            notifyAll();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
