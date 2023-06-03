package cn.az.boot.es.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.az.boot.es.common.RestResponse;
import cn.az.boot.es.entity.UserEs;
import cn.az.boot.es.service.UserService;
import jakarta.annotation.Resource;

/**
 * @author Liz
 * @version 2019/11/29
 */
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/create")
    public RestResponse create(@RequestParam("id") Long id,
            @RequestParam("username") String username,
            @RequestParam("phone") String phone) {
        UserEs user = new UserEs();
        user.setId(id);
        user.setUsername(username);
        user.setPhone(phone);
        return new RestResponse().ok().data(userService.save(user));
    }

    @GetMapping("/names")
    public RestResponse get() {
        List<UserEs> users = new ArrayList<>();
        Iterable<UserEs> userEs = userService.findAll();
        userEs.forEach(users::add);
        return new RestResponse().ok().data(users);
    }

    @GetMapping("/search")
    public RestResponse search(@RequestParam("param") String param) {
        RestResponse response = new RestResponse();
        // NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // queryBuilder.withQuery(QueryBuilders.matchQuery("username", param));
        // Page<UserEs> items = userService.search(queryBuilder.build());
        // response.put("total: ", items.getTotalElements());
        // response.put("items: ", items.get().toArray());
        return response.ok();
    }
}
