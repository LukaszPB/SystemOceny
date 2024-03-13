package com.example.projektgruptest.validator.ocena;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DatesOrderValidator.class)
public @interface ValidDatesOrder {
    String message() default "Dates in wrong order";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
