package com.linkjb.camelcomponent.interview;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ss
 * @Description TODO
 * @Author shark
 * @Data 2022/7/21 14:43
 **/
public class ss {
    public static void main(String[] args) throws InterruptedException {
        //程序计数器
        CountDownLatch countDownLatch = new CountDownLatch(2);

        //2个线程
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 10000; i++) {

            executorService.submit(() -> {
                count.getAndIncrement();//自增
                System.out.println(Thread.currentThread().getName() + " : " + count.get());
                countDownLatch.countDown();
            });
        }


        //线程池 等待10s
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        //关闭线程 其实是将线程状态设置为中断标志  必须等待所有线程处理完任务,才能完全关闭
        executorService.shutdown();

        //必须等待两个线程执行完   会一直等待下去，当然也可以设置指定时间等待超时 await(timeout);
        countDownLatch.await();

    }
}
