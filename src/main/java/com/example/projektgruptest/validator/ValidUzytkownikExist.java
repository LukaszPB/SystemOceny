package com.example.projektgruptest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UzytkownikExistValidator.class)
public @interface ValidUzytkownikExist {
    String message() default "Uzytkownik nie istnieje";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
