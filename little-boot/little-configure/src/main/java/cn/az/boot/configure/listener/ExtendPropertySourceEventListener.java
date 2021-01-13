package cn.az.boot.configure.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.boot.context.event.SpringApplicationEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link SpringApplicationEvent}
 * @author az
 * @since 2020-04-02
 */
public class ExtendPropertySourceEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        MutablePropertySources propertySources = event.getEnvironment().getPropertySources();
        Map<String, Object> source = new HashMap<>(8);

        source.putIfAbsent("user.id", 999);

        MapPropertySource mapPropertySource = new MapPropertySource("my-property-source", source);
        propertySources.addFirst(mapPropertySource);
    }
}
