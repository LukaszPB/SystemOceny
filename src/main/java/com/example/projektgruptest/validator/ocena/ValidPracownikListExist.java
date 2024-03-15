package com.example.projektgruptest.validator.ocena;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PracownikListExistValidator.class)
public @interface ValidPracownikListExist {
    String message() default "List of employees is incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
