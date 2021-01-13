package cn.az.boot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author azusachino
 */
@Slf4j
@SpringBootApplication
public class LittleLogbackApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LittleLogbackApplication.class,args);
	    int len = context.getBeanDefinitionNames().length;
        log.trace("Spring boot启动初始化了 {} 个 Bean", len);
        log.debug("Spring boot启动初始化了 {} 个 Bean", len);
        log.info("Spring boot启动初始化了 {} 个 Bean", len);
        log.warn("Spring boot启动初始化了 {} 个 Bean", len);
        log.error("Spring boot启动初始化了 {} 个 Bean", len);
        try {
            int i = 1;
            int j = 2 / i;
        } catch (Exception e) {
            log.error("【SpringBootDemoLogbackApplication】启动异常：", e);
        }
	}

}
