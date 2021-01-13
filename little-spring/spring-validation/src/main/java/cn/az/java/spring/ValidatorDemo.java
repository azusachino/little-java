package cn.az.java.spring;

import cn.az.java.spring.validator.UserValidator;
import cn.az.java.spring.ioc.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.Locale;
import java.util.Objects;

/**
 * @author az
 * @since 08/05/20
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        // 1. 创建 Validator
        Validator validator = new UserValidator();
        // 2. 判断是否支持目标对象的类型
        User user = new User();
        System.out.println("user 对象是否被 UserValidator 支持检验：" + validator.supports(user.getClass()));
        // 3. 创建 Errors 对象
        Errors errors = new BeanPropertyBindingResult(user, "user");
        validator.validate(user, errors);

        // 4. 获取 MessageSource 对象
        MessageSource messageSource = ErrorsMessageDemo.createMessageSource();

        // 5. 输出所有的错误文案
        for (ObjectError error : errors.getAllErrors()) {
            String message = messageSource.getMessage(Objects.requireNonNull(error.getCode()), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }
}
