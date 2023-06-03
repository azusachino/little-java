package cn.az.boot.app;

import java.util.Collections;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author az
 */
public class LittleAppApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();

        // source ->className, packageName, xml location
        springApplication.setSources(Collections.singleton(Config.class.getName()));
        Properties properties = new Properties();
        properties.setProperty("say", "good");
        springApplication.setDefaultProperties(properties);
        springApplication.run(args);
    }

    @SpringBootApplication
    public static class Config {

    }

}
