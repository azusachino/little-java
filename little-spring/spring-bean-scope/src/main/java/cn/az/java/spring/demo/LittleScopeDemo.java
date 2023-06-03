package cn.az.java.spring.demo;

import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import cn.az.java.spring.domain.User;
import cn.az.java.spring.scope.LittleScope;

/**
 * @author az
 * @since 07/30/20
 */
public class LittleScopeDemo {

    public static final Random R = new Random();

    @Bean
    @Scope(LittleScope.SCOPE_NAME)
    public User u() {
        User u = new User();
        u.setId(R.nextLong());
        return u;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        applicationContext.register(LittleScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(LittleScope.SCOPE_NAME, new LittleScope());
        });

        applicationContext.refresh();

        for (int i = 0; i < 3; i++) {
            Runnable r = () -> System.out.println(applicationContext.getBean(User.class));
            r.run();
        }

        applicationContext.close();
    }
}
