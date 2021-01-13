package cn.az.boot.validation.bean.validator;

import cn.az.boot.validation.bean.constraints.PersonNamePrefix;
import cn.az.boot.validation.domain.Person;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * {@link Person} {@link ConstraintValidator}
 *
 * @author <a href="mailto:azusa146@gmail.com">az</a>
 * @see Person
 * @since 2020-03-12
 */
public class PersonNameValidator implements ConstraintValidator<PersonNamePrefix, String> {

    private String prefix;

    /**
     * Initializes the validator in preparation for
     * {@link #isValid(String, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(PersonNamePrefix constraintAnnotation) {
        this.prefix = constraintAnnotation.value();
    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Objects.isNull(value) || value.startsWith(this.prefix);
    }
}
