package cn.az.boot.configure.listener;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
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
@Order(1)
public class ExternalPropertySourceRunListener implements SpringApplicationRunListener {

    /**
     * Called immediately when the run method has first started. Can be used for very
     * early initialization.
     */
    @Override
    public void starting() {

    }

    /**
     * Called once the environment has been prepared, but before the
     * {@link ApplicationContext} has been created.
     *
     * @param environment the environment
     */
    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
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
     * The context has been refreshed and the application has started but
     * {@link CommandLineRunner CommandLineRunners} and {@link ApplicationRunner
     * ApplicationRunners} have not been called.
     *
     * @param context the application context.
     * @since 2.0.0
     */
    @Override
    public void started(ConfigurableApplicationContext context) {

    }

    /**
     * Called immediately before the run method finishes, when the application context has
     * been refreshed and all {@link CommandLineRunner CommandLineRunners} and
     * {@link ApplicationRunner ApplicationRunners} have been called.
     *
     * @param context the application context.
     * @since 2.0.0
     */
    @Override
    public void running(ConfigurableApplicationContext context) {

    }

    /**
     * Called when a failure occurs when running the application.
     *
     * @param context   the application context or {@code null} if a failure occurred before
     *                  the context was created
     * @param exception the failure
     * @since 2.0.0
     */
    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {

    }
}
