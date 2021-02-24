package com.wucc.island.web.redisdislock.redisson;

import com.wucc.island.common.redisdislock.redisson.DistributedRedisLock;
import com.wucc.island.web.IslandTest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * <p> <p>
 *
 * @version 1.0
 * @auther wudingjia
 * @date 2021-02-24 10:36
 */
@Slf4j
public class RedissonTest extends IslandTest {

    private int count = 0;

    @Test
    public void test01() throws InterruptedException {
        String key = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @SneakyThrows
                @Override
                public void run() {
                    //加锁
                    DistributedRedisLock.acquire(key);
                    //执行具体业务逻辑
                    count++;
                    TimeUnit.SECONDS.sleep(100);
                    //释放锁
                    DistributedRedisLock.release(key);
                    //返回结果

                }
            }.start();
        }
        TimeUnit.SECONDS.sleep(2000);

        System.out.println(count);
    }
}
