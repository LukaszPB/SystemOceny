package com.example.projektgruptest.validator.podkategoria;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatesOrderPKValidator.class)
public @interface ValidDatesOrderPK {
    String message() default "Daty w złej kolejności";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
