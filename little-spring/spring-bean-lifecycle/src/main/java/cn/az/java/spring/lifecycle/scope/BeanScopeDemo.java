package cn.az.java.spring.lifecycle.scope;

import cn.az.java.spring.lifecycle.domain.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author az
 * @since 07/30/20
 */
@Component
public class BeanScopeDemo {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    private User user() {
        return new User();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    private ApplicationContext applicationContext() {
        return new AnnotationConfigApplicationContext();
    }


}
