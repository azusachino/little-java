package cn.az.java.cloud.service.impl;

import cn.az.java.cloud.domain.RestResponse;
import cn.az.java.cloud.domain.User;
import cn.az.java.cloud.service.UserService;
import cn.hutool.core.collection.CollUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author azusachino
 * @version 2019/12/08
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.user-service}")
    private String userServiceUrl;

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser", commandProperties = {
        @HystrixProperty(name = "", value = "")
    })
    public RestResponse getUser(Long id) {
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", RestResponse.class, id);
    }

    /**
     * Gets default user.
     *
     * @param id the id
     * @return the default user
     */
    @Override
    public RestResponse getDefaultUser(Long id) {
        User defaultUser = new User(-1L, "defaultUser", "123456");
        return new RestResponse().data(defaultUser);
    }

    /**
     * Gets user by ids.
     *
     * @param ids the ids
     * @return the user by ids
     */
    @Override
    @HystrixCommand
    public RestResponse getUserByIds(List<Long> ids) {
        log.info("getUserByIds:{}", ids);
        return restTemplate.getForObject(userServiceUrl + "/user/getUserByIds?ids={1}", RestResponse.class, CollUtil.join(ids, ","));
    }

    /**
     * Gets user exception.
     *
     * @param id the id
     * @return the user exception
     */
    @Override
    @HystrixCommand(fallbackMethod = "getCommonUser", ignoreExceptions = {NullPointerException.class})
    public RestResponse getUserException(Long id) {
        if (id == 1) {
            throw new IndexOutOfBoundsException();
        } else if (id == 2) {
            // when npe will return 500
            throw new NullPointerException();
        }
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", RestResponse.class, id);
    }

    public RestResponse getCommonUser(@PathVariable Long id, Throwable e) {
        log.error("getDefaultUser2 id:{},throwable class:{}", id, e.getClass());
        User defaultUser = new User(-2L, "defaultUser2", "123456");
        return new RestResponse().data(defaultUser);
    }

    /**
     * Gets user command.
     *
     * @param id the id
     * @return the user command
     */
    @Override
    @HystrixCommand(fallbackMethod = "getDefaultUser",
        commandKey = "getUserCommand",
        groupKey = "getUserGroup",
        threadPoolKey = "getUserThreadPool")
    public RestResponse getUserCommand(Long id) {
        log.info("getUserCommand id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", RestResponse.class, id);
    }

    /**
     * Gets user cache.
     *
     * @param id the id
     */
    @Override
    @CacheResult(cacheKeyMethod = "getCacheKey")
    @HystrixCommand(fallbackMethod = "getDefaultUser", commandKey = "getUserCache")
    public RestResponse getUserCache(Long id) {
        log.info("getUserCache id:{}", id);
        return restTemplate.getForObject(userServiceUrl + "/user/{1}", RestResponse.class, id);
    }

    /**
     * create cache key
     */
    public String getCacheKey(Long id) {
        return String.valueOf(id);
    }

    /**
     * Remove cache rest response.
     *
     * @param id the id
     */
    @Override
    @CacheRemove(commandKey = "getUserCache", cacheKeyMethod = "getCacheKey")
    @HystrixCommand
    public RestResponse removeCache(Long id) {
        log.info("removeCache id:{}", id);
        return restTemplate.postForObject(userServiceUrl + "/user/delete/{1}", null, RestResponse.class, id);
    }

    @Override
    @HystrixCollapser(batchMethod = "getUserByIds", collapserProperties = {
        @HystrixProperty(name = "timerDelayInMilliseconds", value = "100")
    })
    public Future<User> getUserFuture(Long id) {
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                RestResponse response = restTemplate.getForObject(userServiceUrl + "/user/{1}", RestResponse.class, id);
                assert response != null;
                User user = (User) response.data();
                log.info("getUserById username:{}", user.getUsername());
                return user;
            }
        };
    }

}
