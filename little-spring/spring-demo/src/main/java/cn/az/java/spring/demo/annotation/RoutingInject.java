package cn.az.java.spring.demo.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author az
 * @date 2020/4/2
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RoutingInject {

}
