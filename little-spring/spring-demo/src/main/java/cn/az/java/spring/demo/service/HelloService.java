package cn.az.java.spring.demo.service;

import cn.az.java.spring.demo.annotation.RoutingSwitch;

/**
 * The interface Hello service.
 *
 * @author az
 */
@RoutingSwitch("hello.switch")
public interface HelloService {

    /**
     * Say hello.
     */
    @RoutingSwitch("v1")
    void sayHello();
}
