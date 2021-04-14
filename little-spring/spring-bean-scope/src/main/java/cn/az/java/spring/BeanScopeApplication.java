package cn.az.java.spring;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author az
 * @since 07/30/20
 */
@SpringBootApplication
public class BeanScopeApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
            .main(BeanScopeApplication.class)
            .bannerMode(Banner.Mode.OFF)
            .build(args)
            .run();
    }
}
