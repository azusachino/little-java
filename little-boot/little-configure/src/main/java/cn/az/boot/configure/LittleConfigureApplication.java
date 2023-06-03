package cn.az.boot.configure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * @author az
 */
@SpringBootApplication
@ImportResource(locations = {
        "META-INF/spring/context.xml"
})
public class LittleConfigureApplication implements EnvironmentAware {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(LittleConfigureApplication.class);
        application.setAdditionalProfiles("prod");
        application.run(args);
    }

    @Override
    public void setEnvironment(Environment environment) {
        // get current environment
        System.err.println("current active profile : " + Arrays.asList(environment.getActiveProfiles()));

        if (environment instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
            MutablePropertySources mutablePropertySources = configurableEnvironment.getPropertySources();

            Map<String, Object> map = new HashMap<>();

            map.put("server.port", 9090);

            PropertySource<?> propertySource = new MapPropertySource("java.code", map);

            mutablePropertySources.addFirst(propertySource);
        }
    }
}
