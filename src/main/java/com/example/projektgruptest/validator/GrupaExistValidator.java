package com.example.projektgruptest.validator;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.service.GrupaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class GrupaExistValidator implements ConstraintValidator<ValidGrupaExist, Long> {
    @Autowired
    private GrupaService grupaService;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        try {
            grupaService.getGrupa(id);
            return true;
        }
        catch (ResourceNotFoundException ex) {
            return false;
        }
    }
}