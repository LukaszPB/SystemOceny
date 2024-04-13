package com.example.projektgruptest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GrupaExistValidator.class)
public @interface ValidGrupaExist {
    String message() default "Grupa nie istnieje";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
