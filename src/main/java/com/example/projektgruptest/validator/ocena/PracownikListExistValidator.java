package com.example.projektgruptest.validator.ocena;

import com.example.projektgruptest.service.PracownikService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PracownikListExistValidator implements ConstraintValidator<ValidPracownikListExist, List<Long>> {
    @Autowired
    private PracownikService pracownikService;
    @Override
    public boolean isValid(List<Long> pracownikIdList, ConstraintValidatorContext context) {
        return pracownikIdList.stream()
                .allMatch(id -> pracownikService.getPracownik(id) != null) &&
                pracownikIdList.size() == pracownikIdList.stream().distinct().count();
    }
}
