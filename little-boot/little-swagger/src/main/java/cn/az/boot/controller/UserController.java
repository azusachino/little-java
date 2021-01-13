package cn.az.boot.controller;

import cn.az.boot.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author azusachino
 * @version 12/12/2019
 */
@Api(value = "UserController")
@RestController
@RequestMapping("user")
public class UserController {

    @ApiIgnore
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation(value = "getUserList", notes = "getUserList")
    @GetMapping("/list")
    public  List<User> getUserList() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("az");
        user1.setAge(25);
        list.add(user1);
        User user2 = new User();
        user2.setId(2L);
        user2.setName("chino");
        user2.setAge(20);
        list.add(user2);
        return list;
    }

    @ApiOperation(value = "addUser", notes = "addUserEntity")
    @ApiImplicitParam(name = "user", value = "user", required = true, dataType = "User")
    @PostMapping("/add")
    public Map<String, Object> addUser(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ApiOperation(value = "deleteUser", notes = "deleteUserById")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    public Map<String, Object> deleteUser(@PathVariable(value = "id") Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ApiOperation(value = "updateUser", notes = "updateUserById")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "userId", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "userEntity", required = true, dataType = "User") })
    @PutMapping("/{id}")
    public Map<String, Object> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }
}
