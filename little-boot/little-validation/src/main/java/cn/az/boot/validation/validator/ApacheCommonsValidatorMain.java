package cn.az.boot.validation.validator;

import org.apache.commons.validator.routines.IntegerValidator;

import java.util.Locale;

/**
 * ApacheCommonsValidatorMain
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see org.apache.commons.validator.routines.IntegerValidator
 * @since 2020-03-12
 */
public class ApacheCommonsValidatorMain {

    public static void main(String[] args) {
        IntegerValidator integerValidator = IntegerValidator.getInstance();

        Integer value = integerValidator.validate("10");
        System.out.println(value);

        value = integerValidator.validate("A", Locale.CHINESE);
        System.out.println(value);
    }
}
