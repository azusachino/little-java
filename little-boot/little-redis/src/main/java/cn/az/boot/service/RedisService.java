package cn.az.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.function.Function;

/**
 * @author azusachino
 * @version 12/10/2019
 */
@Service
public class RedisService {

    final JedisPool jedisPool;

    @Autowired
    public RedisService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    private <T> T execute(Function<Jedis, T> j) throws RedisConnectionFailureException {
        try (Jedis jedis = jedisPool.getResource()) {
            return j.apply(jedis);
        } catch (Exception e) {
            throw new RedisConnectionFailureException(e.getMessage());
        }
    }

    public String get(String key) {
        return execute(j -> j.get(key.toLowerCase()));
    }

    public void set(String key, String value) {
        execute(j -> j.set(key.toLowerCase(), value));
    }
}
