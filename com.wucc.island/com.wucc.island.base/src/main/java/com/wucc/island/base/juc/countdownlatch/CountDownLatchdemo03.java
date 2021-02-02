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
public class CountDownLatchdemo03 {

    public static class T extends Thread{

        int runCostTime;
        CountDownLatch commanderCd;
        CountDownLatch countDownLatch;
        public T(String name, int sleepSeconds, CountDownLatch commanderCd,CountDownLatch countDownLatch) {
            super(name);
            this.runCostTime = sleepSeconds;
            this.commanderCd = commanderCd;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            //等待指令员起跑信号
            try{
                commanderCd.await();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            Thread thread = Thread.currentThread();
            long startTime = System.currentTimeMillis();
            System.out.println(startTime+thread.getName()+"开始跑");
            try {
                TimeUnit.SECONDS.sleep(this.runCostTime);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }finally {
                countDownLatch.countDown();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime+thread.getName()+"跑完"+","+"耗时："+(endTime - startTime));
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        System.out.println("开始时间："+startTime);
        //一个指令员
        CountDownLatch commanderCd = new CountDownLatch(1);
        //三个跑步者
        CountDownLatch countDownLatch = new CountDownLatch(3);

        T t1 = new T("小明",2,commanderCd,countDownLatch);
        t1.start();
        T t2 = new T("小红",6,commanderCd,countDownLatch);
        t2.start();
        T t3 = new T("小白",9,commanderCd,countDownLatch);
        t3.start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println(System.currentTimeMillis()+"枪响了，快跑");
        commanderCd.countDown();

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("结束时间"+endTime);
        System.out.println("共耗时："+(endTime - startTime));
    }
}
