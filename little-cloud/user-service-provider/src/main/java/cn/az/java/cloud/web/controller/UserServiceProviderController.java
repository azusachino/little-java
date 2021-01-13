package cn.az.java.cloud.web.controller;

import cn.az.cloud.api.UserService;
import cn.az.cloud.domain.Admin;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

/**
 * @author az
 * @since 2020-04-15
 */
@RestController
public class UserServiceProviderController implements UserService {

    /**
     * 实现 Bean ： InMemoryUserService
     */
    @Autowired
    @Qualifier("inMemoryUserService")
    private UserService userService;


    /**
     * 通过方法继承，URL 映射 ："/user/save"
     *
     * @param admin user
     */
    @Override
    public Boolean saveUser(@RequestBody Admin admin) {
        return userService.saveUser(admin);
    }

    /**
     * 通过方法继承，URL 映射 ："/user/find/all"
     */
    @HystrixCommand(
            commandProperties = { // Command 配置
                    // 设置操作时间为 100 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            fallbackMethod = "fallbackForGetUsers" // 设置 fallback 方法
    )
    @Override
    public Collection<Admin> findAll() {
        return userService.findAll();
    }

    /**
     * 获取所有用户列表
     */
    @HystrixCommand(
            commandProperties = { // Command 配置
                    // 设置操作时间为 100 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "100")
            },
            fallbackMethod = "fallbackForGetUsers" // 设置 fallback 方法
    )
    @GetMapping("/user/list")
    public Collection<Admin> getUsers() throws InterruptedException {
        long executeTime = RandomUtils.nextInt(200);
        // 通过休眠来模拟执行时间
        System.out.println("Execute Time : " + executeTime + " ms");
        Thread.sleep(executeTime);
        return userService.findAll();
    }

    /**
     * {@link #getUsers()} 的 fallback 方法
     *
     * @return 空集合
     */
    public Collection<Admin> fallbackForGetUsers() {
        return Collections.emptyList();
    }
}
