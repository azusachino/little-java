package cn.az.boot.validation.bean;

import cn.az.boot.validation.config.ValidatorConfig;
import cn.az.boot.validation.domain.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.Validator;

/**
 * @author az
 * @since 09/17/20
 */
public class ValidatorTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ValidatorConfig.class);

        applicationContext.refresh();
        Person person = new Person();
        person.setName("az大哥大");
        person.setAge(300L);
        Validator validator = applicationContext.getBean(Validator.class);
        validator.validate(person).forEach(e -> {
            System.out.println(e.getMessage());
        });

        applicationContext.close();
    }
}
