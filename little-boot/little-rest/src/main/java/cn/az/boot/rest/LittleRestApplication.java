package cn.az.boot.rest;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author az
 */
@SpringBootApplication
public class LittleRestApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplicationBuilder(LittleRestApplication.class)
            .web(WebApplicationType.SERVLET)
            .bannerMode(Banner.Mode.OFF)
            .profiles("dev")
            .build();
        application.run(args);
    }
}
