package cn.az.java.spring.demo.controller;

import cn.az.java.spring.demo.annotation.RoutingInject;
import cn.az.java.spring.demo.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author az
 */
@RestController
public class HelloController {

    @RoutingInject
    private HelloService helloService;
}
