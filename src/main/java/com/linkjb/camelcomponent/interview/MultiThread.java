package com.linkjb.camelcomponent.interview;

/**
 * @ClassName MultiThread
 * @Description TODO
 * @Author shark
 * @Data 2022/7/21 17:02
 **/
public class MultiThread {
    private static int num = 0;

    /**不加static
     * 这样线程获得m1和m2两个对象的两个锁
     * param值为a 设置num=100 设置成功
     param值为b 设置num=200 设置成功
     param值=b 设置num=200
     param值=a 设置num=200
     加了static
     param值为a 设置num=100 设置成功
     param值=a 设置num=100
     param值为b 设置num=200 设置成功
     param值=b 设置num=200
     * @param param
     */
    public static synchronized void printNum(String param) {

        try {
            if (param.equals("a")) {
                num = 100;
                System.out.println("param值为a 设置num=100 设置成功");
                Thread.sleep(1000);
            }else {
                num = 200;
                System.out.println("param值为b 设置num=200 设置成功");
            }
            System.out.println("param值="+param+" 设置num="+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MultiThread m1 = new MultiThread();
        final MultiThread m2 = new MultiThread();
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                m1.printNum("a");
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                m2.printNum("b");
            }
        });

        t1.start();
        t2.start();
    }
}
