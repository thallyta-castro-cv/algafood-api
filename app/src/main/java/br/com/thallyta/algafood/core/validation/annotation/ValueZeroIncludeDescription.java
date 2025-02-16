package br.com.thallyta.algafood.core.validation.annotation;

import br.com.thallyta.algafood.core.validation.classes.ValueZeroIncludeDescriptionValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValueZeroIncludeDescriptionValidator.class})
public @interface ValueZeroIncludeDescription {

    String message() default "Descrição obrigatória é inválida!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String fieldValue();
    String fieldDescription();
    String requireDescription();
}
