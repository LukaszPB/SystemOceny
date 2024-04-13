package com.example.projektgruptest.validator.podkategoria;

import com.example.projektgruptest.modelDTO.PodKategoriaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DatesOrderPKValidator implements ConstraintValidator<ValidDatesOrderPK, PodKategoriaDTO> {
    @Override
    public boolean isValid(PodKategoriaDTO podKategoriaDTO, ConstraintValidatorContext context) {
        return  podKategoriaDTO.getDataKoncowa().after(podKategoriaDTO.getDataPoczatkowa());
    }
}