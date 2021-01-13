package cn.az.boot.configure.initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import java.util.HashMap;
import java.util.Map;

/**
 * @author az
 * @since 2020-04-02
 */
public class ExtendPropertySourceInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /**
     * Initialize the given application context.
     *
     * @param applicationContext the application to configure
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        Map<String, Object> source = new HashMap<>(8);

        source.putIfAbsent("user.id", 999);

        MapPropertySource mapPropertySource = new MapPropertySource("my-property-source", source);
        propertySources.addFirst(mapPropertySource);
    }
}
