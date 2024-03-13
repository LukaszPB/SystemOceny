package com.example.projektgruptest.validator.osiagniecie;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserCanAddOsiagniecieValidator.class)
public @interface ValidUserCanAddOsiagniecie {
    String message() default "User can't add this osiagniecie";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
