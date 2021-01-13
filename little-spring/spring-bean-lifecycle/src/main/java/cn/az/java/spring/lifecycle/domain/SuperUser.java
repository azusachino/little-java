package cn.az.java.spring.lifecycle.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author az
 * @date 2020/4/2
 */
@Data
@ToString
@Accessors
@EqualsAndHashCode(callSuper = true)
public class SuperUser extends User {

    private String address;

    private String description;

    @PostConstruct
    public void init() {
        System.out.println("Current SuperUser is initializing");
    }

    @PreDestroy
    public void doDestroy() {
        System.out.println("Current SuperUser is destroying");
    }

}
