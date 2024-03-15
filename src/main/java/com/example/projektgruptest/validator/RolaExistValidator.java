package com.example.projektgruptest.validator;

import com.example.projektgruptest.service.PodKategorieService;
import com.example.projektgruptest.service.RolaService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class RolaExistValidator implements ConstraintValidator<ValidRolaExist, String> {
    @Autowired
    private RolaService rolaService;
    @Override
    public boolean isValid(String rolaNazwa, ConstraintValidatorContext context) {
        return rolaService.getRola(rolaNazwa) != null;
    }
}
