package cn.az.java.cloud.controller;

import cn.az.java.cloud.domain.RestResponse;
import cn.az.java.cloud.domain.User;
import cn.az.java.cloud.service.UserService;
import cn.hutool.core.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author azusachino
 * @version 2019/12/08
 */
@RestController
@RequestMapping("user")
public class UserHystrixController {

    @Autowired
    private UserService userService;

    @GetMapping("/testFallback/{id}")
    public RestResponse testFallback(@PathVariable Long id) {
        return new RestResponse().ok().data(userService.getUser(id));
    }

    @GetMapping("/testCommand/{id}")
    public RestResponse testCommand(@PathVariable Long id) {
        return userService.getUserCommand(id);
    }

    @GetMapping("/testException/{id}")
    public RestResponse testException(@PathVariable Long id) {
        return userService.getUserException(id);
    }

    @GetMapping("/testCache/{id}")
    public RestResponse testCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.getUserCache(id);
        userService.getUserCache(id);
        return new RestResponse().ok().msg("operation success");
    }

    @GetMapping("/testRemoveCache/{id}")
    public RestResponse testRemoveCache(@PathVariable Long id) {
        userService.getUserCache(id);
        userService.removeCache(id);
        userService.getUserCache(id);
        return new RestResponse().ok().msg("operation success");
    }

    @GetMapping("/testCollapse")
    public RestResponse testCollapse() throws ExecutionException, InterruptedException {
        Future<User> future1 = userService.getUserFuture(1L);
        Future<User> future2 = userService.getUserFuture(2L);
        future1.get();
        future2.get();
        ThreadUtil.safeSleep(200);
        Future<User> future3 = userService.getUserFuture(3L);
        future3.get();
        return new RestResponse().ok().msg("operation success");
    }


}
