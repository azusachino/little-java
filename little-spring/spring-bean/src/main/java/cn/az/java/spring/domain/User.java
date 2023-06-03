package cn.az.java.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.BeanNameAware;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * @author az
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements BeanNameAware {

    private Long id;
    private String name;

    private transient String beanName;

    public static User createUser() {
        User u = new User();
        u.setId(1L);
        u.setName("az");
        return u;
    }

    @PostConstruct
    public void init() {
        System.err.println("user bean " + beanName + " init");
    }

    @PreDestroy
    public void destroy() {
        System.err.println("user bean " + beanName + " destroy");
    }

}
