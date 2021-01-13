package cn.az.boot.rest.controller;

import cn.az.boot.rest.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User {@link RestController}
 *
 * @author az
 */
@RestController
public class UserRestController {

    @PostMapping(value = "/echo/user",
        consumes = "application/xml;charset=UTF-8",
        produces = "application/json;charset=UTF-8")
    public User user(@RequestBody User user) {
        return user;
    }

}
