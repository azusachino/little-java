package cn.az.boot.thymeleaf.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author az
 * @since 2020-03-30
 */
@SpringBootApplication(scanBasePackages = {"cn.az.boot.thymeleaf"})
public class HelloWorldBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldBootstrap.class, args);
    }
}
