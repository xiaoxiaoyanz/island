package com.wucc.island.base.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-31 10:51
 */
public class CountDownLatchdemo02 {

    public static class T extends Thread{

        int sleepSeconds;
        CountDownLatch countDownLatch;
        public T(String name, int sleepSeconds, CountDownLatch countDownLatch) {
            super(name);
            this.sleepSeconds = sleepSeconds;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            Thread thread = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println("线程:"+thread.getName()+"开始处理");
            try {
                TimeUnit.SECONDS.sleep(this.sleepSeconds);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("线程："+thread.getName()+"处理完毕"+","+"耗时："+(endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(2);
        long startTime = System.currentTimeMillis();
        T t1 = new T("t1",2,countDownLatch);
        t1.start();
        T t2 = new T("t2",6,countDownLatch);
        t2.start();
        final boolean await = countDownLatch.await(3, TimeUnit.SECONDS);
        System.out.println(await);
        long endTime = System.currentTimeMillis();
        System.out.println("共耗时："+(endTime - startTime));
    }
}
