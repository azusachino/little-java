package cn.az.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author az
 */
@EnableCaching
@SpringBootApplication
public class LittleRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittleRedisApplication.class, args);
    }

}
