package com.example.projektgruptest.validator.ocena;

import com.example.projektgruptest.modelDTO.OcenaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DatesOrderValidator implements ConstraintValidator<ValidDatesOrder, OcenaDTO> {
    @Override
    public boolean isValid(OcenaDTO ocenaDTO, ConstraintValidatorContext context) {
        return ocenaDTO.getDataPoczatkowa().before(ocenaDTO.getDataKoncowa());
    }
}
