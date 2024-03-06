package com.example.projektgruptest.validator;

import com.example.projektgruptest.service.PracownikService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

public class PracownikExistValidator implements ConstraintValidator<ValidPracownikExist, Long> {
    @Autowired
    private PracownikService pracownikService;

    @Override
    public boolean isValid(Long idPracownika, ConstraintValidatorContext constraintValidatorContext) {
        return pracownikService.getPracownik(idPracownika) != null;
    }
}
