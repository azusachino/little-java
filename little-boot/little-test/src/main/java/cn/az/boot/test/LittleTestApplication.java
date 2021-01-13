package cn.az.boot.test;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author az
 */
@SpringBootApplication
public class LittleTestApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplicationBuilder()
            .main(LittleTestApplication.class)
            .web(WebApplicationType.SERVLET)
            .bannerMode(Banner.Mode.LOG)
            .profiles("dev")
            .build(args);

        application.run();
    }

}
