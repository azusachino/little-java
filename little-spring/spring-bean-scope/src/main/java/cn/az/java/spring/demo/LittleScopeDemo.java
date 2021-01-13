package cn.az.java.spring.demo;

import cn.az.java.spring.domain.User;
import cn.az.java.spring.scope.LittleScope;
import cn.hutool.core.util.RandomUtil;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author az
 * @since 07/30/20
 */
public class LittleScopeDemo {

    @Bean
    @Scope(LittleScope.SCOPE_NAME)
    public User u() {
        User u = new User();
        u.setId(RandomUtil.randomLong());
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
