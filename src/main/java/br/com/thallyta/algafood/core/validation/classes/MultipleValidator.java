package br.com.thallyta.algafood.core.validation.classes;

import br.com.thallyta.algafood.core.validation.annotation.Multiple;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultipleValidator implements ConstraintValidator<Multiple, Number> {

    private int numberMultiple;

    @Override
    public void initialize(Multiple constraintAnnotation) {
        this.numberMultiple = constraintAnnotation.number();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;

        if(value != null) {
            BigDecimal decimalValue = BigDecimal.valueOf(value.doubleValue());
            BigDecimal decimalMultiple = BigDecimal.valueOf(this.numberMultiple);
            BigDecimal remainder = decimalValue.remainder(decimalMultiple);

            valid = BigDecimal.ZERO.compareTo(remainder) == 0;
        }

        return valid;
    }
}
