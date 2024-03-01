package com.example.projektgruptest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WniosekExistValidator.class)
public @interface ValidWniosekExist {
    String message() default "Application does not exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
