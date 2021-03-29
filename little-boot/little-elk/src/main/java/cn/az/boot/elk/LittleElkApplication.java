package cn.az.boot.elk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author az
 * @since 03/29/21 23:03
 */
@RestController
@SpringBootApplication
public class LittleElkApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LittleElkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LittleElkApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        LOGGER.info("access hello controller");
        return "hello";
    }
}
