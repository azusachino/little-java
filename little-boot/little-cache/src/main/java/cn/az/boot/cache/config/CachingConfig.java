package cn.az.boot.cache.config;

import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.*;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;
import java.util.Arrays;

/**
 * @author az
 * @date 2020-03-11
 */
@EnableCaching
@Configuration
public class CachingConfig extends CachingConfigurerSupport {

    @Bean
    public RedisCacheManagerBuilderCustomizer customizer() {
        return builder -> builder.
                withCacheConfiguration("cache1",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(20)))
                .withCacheConfiguration("cache2",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(1)));
    }

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new SimpleCacheManager();
    }

    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver();
    }

    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            Arrays.stream(params).map(Object::toString).forEach(sb::append);
            return sb.toString();
        };
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }
}
