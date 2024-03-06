package com.example.projektgruptest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserCanAddToWniosekValidator.class)
public @interface ValidUserCanAddToWnisek {
    String message() default "User can't access wniosek";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
