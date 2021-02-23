/*
package com.wucc.island.common.configure;

import com.yonyougov.apsl.common.system.SystemProperties;
import com.yonyougov.apsl.web.condition.CacheEnableCondition;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Method;

*/
/**
 * 缓存配置
 * 
 * @author zhoujiej
 * @date 2020/06/18
 *//*

@Configuration
@Conditional(CacheEnableCondition.class)
@EnableCaching
public class CacheConfig {

    @Bean(name = "cwCacheManager")
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 设置系统缓存自定义前缀
        RedisCacheConfiguration redisCacheConfiguration =
            RedisCacheConfiguration.defaultCacheConfig().computePrefixWith(cacheKeyPrefix());
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
            .cacheDefaults(redisCacheConfiguration).build();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public CacheKeyPrefix cacheKeyPrefix() {
        return new CacheKeyPrefix() {
            @Override
            public String compute(String cacheName) {
                StringBuilder sBuilder = new StringBuilder(100);
                sBuilder.append(SystemProperties.SYS_TYPE.toUpperCase()).append(":");
                sBuilder.append(cacheName).append(":");
                return sBuilder.toString();
            }
        };
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(":").append(method.getName());
                sb.append(":");
                for (Object obj : params) {
                    if (obj == null) {
                        obj = "@null@";
                    }
                    sb.append("_").append(obj.toString());
                }
                return sb.toString();
            }
        };
    }
}
*/
