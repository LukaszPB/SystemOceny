package com.example.projektgruptest.validator.ocena;

import com.example.projektgruptest.modelDTO.DodawanieOcenDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DatesOrderValidator implements ConstraintValidator<ValidDatesOrder, DodawanieOcenDTO> {
    @Override
    public boolean isValid(DodawanieOcenDTO dodawanieOcenDTO, ConstraintValidatorContext context) {
        return dodawanieOcenDTO.getDataPoczatkowa().before(dodawanieOcenDTO.getDataKoncowa());
    }
}
