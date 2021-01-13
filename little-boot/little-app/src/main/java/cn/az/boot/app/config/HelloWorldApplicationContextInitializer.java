package cn.az.boot.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * @author az
 */
@Slf4j
@Order(value = Integer.MAX_VALUE - 10)
public class HelloWorldApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.warn("Hello world application id : {}", applicationContext.getId());
    }
}
