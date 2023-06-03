package cn.az.boot.app.run;

import java.time.Duration;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * @author az
 */
@Order(0)
@Slf4j
public class HelloWorldRunListener implements SpringApplicationRunListener {

    @Override
    public void started(ConfigurableApplicationContext context, Duration timeTaken) {
        log.info("starting...");
    }

}
