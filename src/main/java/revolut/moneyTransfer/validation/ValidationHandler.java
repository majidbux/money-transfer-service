package revolut.moneyTransfer.validation;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import revolut.moneyTransfer.exception.ValidationException;
import spark.utils.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Singleton
public class ValidationHandler<T> {

    private final Validator validator;

    @Inject
    public ValidationHandler(Validator validator) {
        this.validator = validator;
    }

    public void validateRequest(final T t) throws ValidationException {
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        for (ConstraintViolation<T> violation : violations) {
            if (!StringUtils.isEmpty(violation.getMessage())){
                throw new ValidationException(violation.getMessage());
            }
        }
    }
}
