package cn.az.boot.configure.listener;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

/**
 * @author az
 * @since 2020-04-02
 */
@Order(1)
public class ExternalPropertySourceRunListener implements SpringApplicationRunListener {

    /**
     * Called once the environment has been prepared, but before the
     * {@link ApplicationContext} has been created.
     *
     * @param environment the environment
     */
    @Override
    public void environmentPrepared(ConfigurableBootstrapContext bootstrapContext,
            ConfigurableEnvironment environment) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> source = new HashMap<>(8);

        source.putIfAbsent("user.id", 999);

        MapPropertySource mapPropertySource = new MapPropertySource("my-property-source", source);
        propertySources.addFirst(mapPropertySource);
    }

    /**
     * Called once the {@link ApplicationContext} has been created and prepared, but
     * before sources have been loaded.
     *
     * @param context the application context
     */
    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        Map<String, Object> source = new HashMap<>(8);

        source.putIfAbsent("user.id", 999);

        MapPropertySource mapPropertySource = new MapPropertySource("my-property-source", source);
        propertySources.addFirst(mapPropertySource);
    }

    /**
     * Called once the application context has been loaded but before it has been
     * refreshed.
     *
     * @param context the application context
     */
    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        MutablePropertySources propertySources = context.getEnvironment().getPropertySources();
        Map<String, Object> source = new HashMap<>(8);

        source.putIfAbsent("user.id", 999);

        MapPropertySource mapPropertySource = new MapPropertySource("my-property-source", source);
        propertySources.addFirst(mapPropertySource);
    }

    /**
     * Called when a failure occurs when running the application.
     *
     * @param context   the application context or {@code null} if a failure
     *                  occurred before
     *                  the context was created
     * @param exception the failure
     * @since 2.0.0
     */
    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
