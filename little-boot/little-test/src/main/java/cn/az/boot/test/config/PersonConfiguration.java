package cn.az.boot.test.config;

import cn.az.boot.test.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author az
 */
@Configuration
public class PersonConfiguration {

    @Bean("mainPerson")
    public Person person() {
        Person p = new Person();
        p.setAge(12);
        p.setName("aw");
        return p;
    }
}
