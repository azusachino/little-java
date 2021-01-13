package cn.az.boot.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author az
 * @since 2020-03-30
 */
@Controller
public class HelloWorldController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "hello-world/index";
    }

    @ModelAttribute(name = "message")
    public String message() {
        return "Hello, Thymeleaf";
    }
}
