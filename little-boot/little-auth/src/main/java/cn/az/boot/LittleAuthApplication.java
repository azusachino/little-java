package cn.az.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Liz
 */
@SpringBootApplication
public class LittleAuthApplication implements ApplicationRunner {

    @Value("${spring.application.name}")
    private String application;

    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(LittleAuthApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println(application + " activated: http://localhost:" + port);
    }
}
