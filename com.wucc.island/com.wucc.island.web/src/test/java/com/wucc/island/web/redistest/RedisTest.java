package com.wucc.island.web.redistest;

import com.wucc.island.entity.ministry.AdtfMinistryBill;
import com.wucc.island.web.IslandTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * <p>
 * Redis测试
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-11-15 17:17
 */
@Slf4j
public class RedisTest extends IslandTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    /**
     * 测试 Redis 操作
     */
    @Test
    public void get() {
        // 测试线程安全，程序结束查看redis中count的值是否为1000
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i -> executorService.execute(() -> stringRedisTemplate.opsForValue().increment("count", 1)));

        stringRedisTemplate.opsForValue().set("k2", "v2");
        String k2 = stringRedisTemplate.opsForValue().get("k2");
        log.debug("【k2】= {}", k2);

        // 以下演示整合，具体Redis命令可以参考官方文档
        String key = "wucc:ministry:1";
        AdtfMinistryBill adtfMinistryBillSet = new AdtfMinistryBill();
        adtfMinistryBillSet.setId(12312312312L);
        redisCacheTemplate.opsForValue().set(key, adtfMinistryBillSet);
        // 对应 String（字符串）
        AdtfMinistryBill adtfMinistryBill = (AdtfMinistryBill) redisCacheTemplate.opsForValue().get(key);
        log.debug("【adtfMinistryBill】= {}", adtfMinistryBill);
    }

    @Test
    public void test01(){

        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();

        stringStringValueOperations.set("k1","v1");

        System.out.println(stringStringValueOperations.get("k1"));

    }
}
