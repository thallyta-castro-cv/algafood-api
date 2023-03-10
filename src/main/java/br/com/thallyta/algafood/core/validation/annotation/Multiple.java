package br.com.thallyta.algafood.core.validation.annotation;

import br.com.thallyta.algafood.core.validation.classes.MultipleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {MultipleValidator.class})
public @interface Multiple {

    String message() default "{Multiple.notValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int number();

}
