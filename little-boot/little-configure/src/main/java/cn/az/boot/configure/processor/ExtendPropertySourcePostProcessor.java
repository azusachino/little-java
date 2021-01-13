package cn.az.boot.configure.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * @author az
 * @since 2020-04-02
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 9)
public class ExtendPropertySourcePostProcessor implements EnvironmentPostProcessor {

    /**
     * Post-process the given {@code environment}.
     *
     * @param environment the environment to post-process
     * @param application the application to which the environment belongs
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>(8);

        source.putIfAbsent("user.id", 999);

        MapPropertySource mapPropertySource = new MapPropertySource("my-property-source", source);
        propertySources.addFirst(mapPropertySource);
    }
}
