package com.example.projektgruptest.validator.podkategoria;

import com.example.projektgruptest.validator.dodawanieOsiagniec.IloscPunktowDValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProgiPunktowValidator.class)
public @interface ValidProgiPunktow {
    String message() default "Invalid amount of points";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
