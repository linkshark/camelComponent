package com.linkjb.camelcomponent.pool.demo3;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    static AtomicInteger order = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://www.linkjb.com:3307/pool";
        String username = "root";
        String password = "Shark1996!";

        DbConfig dbConnectionConfig = new DbConfig(
                driver, url, username, password);
        DbConnectionFactory dbConnectionFactory = new DbConnectionFactory(dbConnectionConfig);

        DbPoolConfig dbPoolConfig = new DbPoolConfig();
        dbPoolConfig.setMaxTotal(10);
        dbPoolConfig.setMaxIdle(5);
        dbPoolConfig.setMinIdle(2);
        dbPoolConfig.setMaxWaitMillis(1000);
        dbPoolConfig.setTestOnBorrow(false);
        dbPoolConfig.setTestOnReturn(false);

        DbPool dbPool = new DbPool(dbPoolConfig, dbConnectionFactory);


//        for(int i = 0 ; i < 20 ; i++){
//            Connection connection = dbPool.getConnection();
//            Statement statement = connection.createStatement();
//            statement.execute("insert into t_person(name, age) values ('a', 20)");
//            dbPool.returnConnection(connection);
//        }

        ThreadPoolExecutor threadPools = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("拿不到连接咯");
            }
        });

        for (int i = 0; i < 100; i++) {
            threadPools.submit(new Runnable() {
                @Override
                public void run() {
                    Connection connection = null;
                    try {
                        connection = dbPool.getConnection();
                        Statement statement = connection.createStatement();
                        statement.execute("insert into t_person(name, age) values ('" + Thread.currentThread().getName() + "', " + order.addAndGet(1) + ")");
                        //Thread.sleep(2500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        dbPool.returnConnection(connection);
                    }
                }
            });
        }
        threadPools.shutdown();


    }
}
