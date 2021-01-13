package cn.az.boot.app.run;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.core.annotation.Order;

/**
 * @author az
 */
@Order(0)
@Slf4j
public class HelloWorldRunListener implements SpringApplicationRunListener {

    @Override
    public void starting() {
        log.warn("Hello World Listener...");
    }
}
