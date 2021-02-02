package com.wucc.island.base.juc.countdownlatch;

import java.util.concurrent.TimeUnit;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-31 10:51
 */
public class CountDownLatchTro {

    public static class T extends Thread{

        int sleepSeconds;
        public T(String name,int sleepSeconds) {
            super(name);
            this.sleepSeconds = sleepSeconds;
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
            }
            long endTime = System.currentTimeMillis();
            System.out.println("线程："+thread.getName()+"处理完毕"+","+"耗时："+(endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        T t1 = new T("t1",2);

        T t2 = new T("t2",6);

        t1.start();

        t2.start();

        t1.join();

        t2.join();

        long endTime = System.currentTimeMillis();

        System.out.println("共耗时："+(endTime - startTime));
    }
}
