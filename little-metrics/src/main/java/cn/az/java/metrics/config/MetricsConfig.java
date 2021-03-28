package cn.az.java.metrics.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author az
 * @since 03/28/21 10:43
 */
@Configuration
public class MetricsConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> configurer() {
        return registry -> registry.config().commonTags("application", applicationName);
    }
}
