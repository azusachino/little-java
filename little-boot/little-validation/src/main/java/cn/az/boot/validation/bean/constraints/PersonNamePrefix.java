package cn.az.boot.validation.bean.constraints;

import cn.az.boot.validation.bean.validator.PersonNameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@link Constraint}
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see PersonNameValidator
 * @since 2020-03-12
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PersonNameValidator.class)
public @interface PersonNamePrefix {

    String message() default "{person.name.prefix.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "sf-";
}
