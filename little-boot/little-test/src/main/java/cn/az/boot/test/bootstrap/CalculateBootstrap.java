package cn.az.boot.test.bootstrap;

import cn.az.boot.test.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author az
 */
@SpringBootApplication(scanBasePackages = "cn.az.boot.test")
public class CalculateBootstrap {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateBootstrap.class)
            .web(WebApplicationType.NONE)
            .profiles("java7")
            .run(args);

        CalculateService calculateService = context.getBean(CalculateService.class);

        System.out.println("sssss, :" + calculateService.sum(1, 2, 3, 4, 5));

    }
}
