package com.wucc.island.base.juc.countdownlatch;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p> 并行任务处理工具类 <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-01-31 11:41
 */
public class TaskDisposeUtils {

    //并行线程数
    public static final int POOL_SIZE;

    static {
        POOL_SIZE = Integer.max(Runtime.getRuntime().availableProcessors(),5);
    }

    public static <T> void dispose(List<T> taskList, Consumer<T> consumer) throws InterruptedException {

        dispose(true,POOL_SIZE,taskList,consumer);
    }

    public static <T> void dispose(Boolean moreThread,int poolSize,List<T> taskList,Consumer<T> consumer) throws InterruptedException {
        if (CollectionUtils.isEmpty(taskList)){
            return;
        }
        if(moreThread && poolSize > 1){
            poolSize = Math.min(poolSize,taskList.size());
            ExecutorService executorService = null;
            try{
                executorService = Executors.newFixedThreadPool(poolSize);
                CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
                for (T t : taskList) {
                    executorService.execute(() -> {
                        try {
                            consumer.accept(t);
                        }finally {
                            countDownLatch.countDown();
                        }
                    });
                }
                countDownLatch.await();
            }finally {
                if(executorService != null){
                    executorService.shutdown();
                }
            }
        }else {
            for (T t : taskList) {
                consumer.accept(t);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        List<Integer> integerList = Stream.iterate(1, a -> a + 1).limit(20).collect(Collectors.toList());

        TaskDisposeUtils.dispose(integerList,item -> {
            try {
                long startTime = System.currentTimeMillis();
                TimeUnit.SECONDS.sleep(item);
                long endTime = System.currentTimeMillis();
                System.out.println("任务"+item+"执行完毕，耗时"+(endTime - startTime));
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });

        System.out.println(integerList+"中任务处理完毕");
    }
}
