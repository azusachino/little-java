package cn.az.boot.shiro.common;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 **/
@ControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class CommonExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public String authorizationExceptionHandler() {
        return "403";
    }
}
