package cn.az.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Liz
 */
@EnableScheduling
@SpringBootApplication
public class LittleQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleQuartzApplication.class, args);
    }

}
