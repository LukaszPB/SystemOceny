package com.example.projektgruptest.validator;

import com.example.projektgruptest.service.WniosekService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class WniosekExistValidator implements ConstraintValidator<ValidWniosekExist, Long> {
    @Autowired
    private WniosekService wniosekService;
    @Override
    public boolean isValid(Long idWniosku, ConstraintValidatorContext context) {
        return wniosekService.getWniosek(idWniosku) != null;
    }
}