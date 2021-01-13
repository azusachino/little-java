package cn.az.boot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author az
 * @date 2020/3/14
 */
@Controller
public class SecurityController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {

        return "index";
    }

}
