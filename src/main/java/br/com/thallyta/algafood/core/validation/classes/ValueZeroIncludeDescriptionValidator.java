package br.com.thallyta.algafood.core.validation.classes;

import br.com.thallyta.algafood.core.validation.annotation.ValueZeroIncludeDescription;
import org.springframework.beans.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;
import java.math.BigDecimal;
import java.util.Objects;

public class ValueZeroIncludeDescriptionValidator implements ConstraintValidator<ValueZeroIncludeDescription, Object> {

    private String fieldValue;
    private String fieldDescription;
    private String requireDescription;

    @Override
    public void initialize(ValueZeroIncludeDescription constraintAnnotation) {
        this.fieldValue = constraintAnnotation.fieldValue();
        this.fieldDescription = constraintAnnotation.fieldDescription();
        this.requireDescription = constraintAnnotation.requireDescription();
    }

    @Override
    public boolean isValid(Object validationObject, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        try {
            BigDecimal value = (BigDecimal) Objects.requireNonNull(BeanUtils.getPropertyDescriptor(validationObject.getClass(), fieldValue))
                    .getReadMethod().invoke(validationObject);

            String description = (String)  Objects.requireNonNull(BeanUtils.getPropertyDescriptor(validationObject.getClass(), fieldDescription))
                    .getReadMethod().invoke(validationObject);

            if(value != null && BigDecimal.ZERO.compareTo(value) == 0 && description != null){
                valid = description.toLowerCase().contains(this.requireDescription.toLowerCase());
            }

            return valid;

        } catch (Exception e) {
            throw new ValidationException(e);
        }
    }
}
