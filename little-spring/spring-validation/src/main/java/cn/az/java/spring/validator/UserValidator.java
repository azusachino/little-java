package cn.az.java.spring.validator;

import cn.az.java.spring.ioc.domain.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author az
 * @since 08/05/20
 */
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");
    }
}
