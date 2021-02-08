package com.wucc.island.base.juc.threadpoolexecutor;

import com.wucc.island.base.juc.countdownlatch.CountDownLatchdemo03;

import java.util.concurrent.*;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-04 14:22
 */
public class ThreadPoolExecutorDemo01 {

    //线程池的简单使用
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            4,
            5,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(20),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String taskName = "任务" + i;
            int finalI = i;
            threadPoolExecutor.execute(() ->{
                try{
                    TimeUnit.SECONDS.sleep(finalI);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+taskName+"处理完毕");
            });
        }
        threadPoolExecutor.shutdown();
    }

}
