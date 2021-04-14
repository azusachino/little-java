package cn.az.boot.cache.config;

import cn.az.boot.cache.entity.Person;
import io.lettuce.core.support.LettuceFactoryBeanSupport;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author az
 * @since 2020-04-20
 */
@Configuration
public class RedisConfig extends LettuceFactoryBeanSupport<Object> {

    @Bean
    public RedisTemplate<String, Person> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        return new JedisConnectionFactory(configuration);
    }

    /**
     * This abstract method declaration mirrors the method in the FactoryBean
     * interface, for a consistent offering of abstract template methods.
     *
     * @see FactoryBean#getObjectType()
     */
    @Override
    public Class<?> getObjectType() {
        return null;
    }

    /**
     * Template method that subclasses must override to construct
     * the object returned by this factory.
     * <p>Invoked on initialization of this FactoryBean in case of
     * a singleton; else, on each {@link #getObject()} call.
     *
     * @return the object returned by this factory
     * @throws Exception if an exception occurred during object creation
     * @see #getObject()
     */
    @Override
    protected Object createInstance() throws Exception {
        return null;
    }
}
