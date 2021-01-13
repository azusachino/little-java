package cn.az.boot.cache.repository;

import cn.az.boot.cache.entity.Person;
import cn.hutool.core.map.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * The type Person repository.
 *
 * @author az
 * @date 2020-03-11
 */
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final Map<String, Person> repository = MapUtil.newHashMap();

    @Autowired
    private RedisTemplate<String, Person> redisTemplate;

    private ValueOperations<String, Person> valueOperations;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    @Cacheable(value = {"persons"})
    public Person findPerson(String id) {
        return repository.getOrDefault(id, new Person());
    }

    @Override
    @CachePut(value = {"persons"})
    public Person savePerson(Person entity) {
        valueOperations.set(entity.getId(), entity);
        return repository.putIfAbsent(entity.getId(), entity);
    }

    /**
     * delete person
     *
     * @param id ID
     */
    @Override
    @CacheEvict(value = {"persons"})
    public Boolean deletePerson(String id) {
        return valueOperations.getOperations().delete(id);
    }

}
