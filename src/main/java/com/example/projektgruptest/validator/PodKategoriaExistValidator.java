package com.example.projektgruptest.validator;

import com.example.projektgruptest.service.PodKategorieService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class PodKategoriaExistValidator implements ConstraintValidator<ValidPodKategoriaExist, String> {
    @Autowired
    private PodKategorieService podKategorieService;
    @Override
    public boolean isValid(String podKategoriaNazwa, ConstraintValidatorContext context) {
        return podKategorieService.getPodkategoria(podKategoriaNazwa) != null;
    }
}
