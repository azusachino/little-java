package cn.az.java.spring.demo.service.impl;

import cn.az.java.spring.demo.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author az
 */
@Service
public class HelloService2Impl implements HelloService {


    @Override
    public void sayHello() {
        System.out.println("hello from v2");
    }
}
