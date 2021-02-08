package com.wucc.island.base.juc.threadpoolexecutor;

import java.sql.Timestamp;
import java.util.concurrent.*;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-04 14:22
 */
public class ThreadPoolExecutorDemo02 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            int j = i;
            String taskName = "任务" + i;
            executorService.execute(() ->{
                System.out.println(Thread.currentThread().getName()+"处理"+taskName);
                try {
                    TimeUnit.SECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

}
