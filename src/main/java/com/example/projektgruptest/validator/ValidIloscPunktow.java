package com.example.projektgruptest.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IloscPunktowValidator.class)
public @interface ValidIloscPunktow {
    String message() default "Invalid amount of points";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
