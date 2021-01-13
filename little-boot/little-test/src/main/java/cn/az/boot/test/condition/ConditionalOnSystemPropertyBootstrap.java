package cn.az.boot.test.condition;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author az
 */
public class ConditionalOnSystemPropertyBootstrap {


    @Bean
    @ConditionalOnSystemProperty(name = "user.name", value = "azusachino")
    public String hello() {
        return "Hello, World";
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ConditionalOnSystemPropertyBootstrap.class)
            .web(WebApplicationType.NONE)
            .run(args);
        String a = context.getBean("hello", String.class);

        System.out.println(a);
    }
}
