package cn.az.java.spring.demo.service.impl;

import cn.az.java.spring.demo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author az
 * @date 2020/4/2
 */
@Service
public class HelloService1Impl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("hello from v1");
    }
}
