package br.com.thallyta.algafood.core.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Payload;
import jakarta.validation.constraints.PositiveOrZero;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@PositiveOrZero
public @interface ShippingFee {

    @OverridesAttribute(constraint = PositiveOrZero.class, name = "message")
    String message() default "{ShippingFee.notValid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
