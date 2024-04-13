package com.example.projektgruptest.validator.podkategoria;

import com.example.projektgruptest.modelDTO.PodKategoriaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProgiPunktowValidator implements ConstraintValidator<ValidProgiPunktow, PodKategoriaDTO> {
    @Override
    public boolean isValid(PodKategoriaDTO podKategoriaDTO, ConstraintValidatorContext context) {
        int min = podKategoriaDTO.getMinPunktow();
        int max = podKategoriaDTO.getMaxPunktow();
        return  min >= 0 && max >= min;
    }
}