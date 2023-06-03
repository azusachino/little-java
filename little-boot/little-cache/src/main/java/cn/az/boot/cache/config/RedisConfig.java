package cn.az.boot.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import cn.az.boot.cache.entity.Person;

/**
 * @author az
 * @since 2020-04-20
 */
@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate<String, Person> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        return new JedisConnectionFactory(configuration);
    }

}
