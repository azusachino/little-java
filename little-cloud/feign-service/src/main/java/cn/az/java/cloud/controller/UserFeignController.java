package cn.az.java.cloud.controller;

import cn.az.java.cloud.domain.RestResponse;
import cn.az.java.cloud.service.UserService;
import cn.az.java.cloud.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@RestController
@RequestMapping("user")
public class UserFeignController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public RestResponse getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/getByUsername")
    public RestResponse getByUsername(@RequestParam String username) {
        return userService.getByUsername(username);
    }

    @PostMapping("/create")
    public RestResponse create(@RequestBody User user) {
        return userService.create(user);
    }

    @PostMapping("/update")
    public RestResponse update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/delete/{id}")
    public RestResponse delete(@PathVariable Long id) {
        return userService.delete(id);
    }

}
