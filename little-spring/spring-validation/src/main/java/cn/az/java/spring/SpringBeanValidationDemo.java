package cn.az.java.spring;

import cn.az.java.spring.ioc.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * @author az
 * @since 08/05/20
 */
public class SpringBeanValidationDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-validation-context.xml");

//        Validator validator = applicationContext.getBean(Validator.class);
//        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        userProcessor.process(new User());

        // 关闭应用上下文
        applicationContext.close();
    }

    @Component
    @Validated
    static class UserProcessor {

        public void process(@Valid User user) {
            System.out.println(user);
        }

    }

}
