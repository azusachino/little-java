package cn.az.boot.controller;

import cn.az.boot.entity.User;
import cn.az.boot.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@Slf4j
@RestController
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping(value = "test")
    public String test() {
        User user = new User();
        user.setEmail("pq@qq.com");
        user.setAge(18);
        user.setName("peq");
        userService.save(user);
        List<User> list = userService.list(new LambdaQueryWrapper<>(new User()).select(User::getId, User::getName));

        list.forEach(u -> log.info("当前用户数据:{}", u));
        // active record
        user.selectAll().forEach(System.out::println);
        return "pq@qq.com";
    }
}
