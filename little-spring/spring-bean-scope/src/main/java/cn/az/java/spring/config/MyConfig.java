package cn.az.java.spring.config;

import cn.az.java.spring.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author az
 * @since 07/30/20
 */
@Component
public class MyConfig {

    @Bean
    public User u() {
        return new User();
    }
}
