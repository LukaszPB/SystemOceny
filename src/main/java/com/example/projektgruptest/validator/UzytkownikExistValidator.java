package com.example.projektgruptest.validator;

import com.example.projektgruptest.service.PracownikService;
import com.example.projektgruptest.service.UzytkownikService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

public class UzytkownikExistValidator implements ConstraintValidator<ValidUzytkownikExist, Long> {
    @Autowired
    private UzytkownikService uzytkownikService;

    @Override
    public boolean isValid(Long idUzytkownika, ConstraintValidatorContext constraintValidatorContext) {
        return uzytkownikService.getUzytkownik(idUzytkownika) != null;
    }
}
