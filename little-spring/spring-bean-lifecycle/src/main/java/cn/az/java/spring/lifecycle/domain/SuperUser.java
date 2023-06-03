package cn.az.java.spring.lifecycle.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * @author az
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
