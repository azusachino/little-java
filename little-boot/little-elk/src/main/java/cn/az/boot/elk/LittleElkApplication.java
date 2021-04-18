package cn.az.boot.elk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * @author az
 * @since 03/29/21 23:03
 */
@RestController
@SpringBootApplication
public class LittleElkApplication {

    private static final Logger LOGGER = Logger.getLogger(LittleElkApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(LittleElkApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        LOGGER.info("access hello controller");
        return "hello";
    }
}
