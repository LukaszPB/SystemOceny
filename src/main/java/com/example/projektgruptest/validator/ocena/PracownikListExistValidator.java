package com.example.projektgruptest.validator.ocena;

import com.example.projektgruptest.modelDTO.PracownikDTO;
import com.example.projektgruptest.service.PracownikService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PracownikListExistValidator implements ConstraintValidator<ValidPracownikListExist, List<PracownikDTO>> {
    @Autowired
    private PracownikService pracownikService;
    @Override
    public boolean isValid(List<PracownikDTO> pracownikDTOList, ConstraintValidatorContext context) {
        return pracownikDTOList.stream()
                .allMatch(pracownikDTO -> pracownikService.getPracownik(pracownikDTO.getId()) != null) &&
                pracownikDTOList.size() == pracownikDTOList.stream().map(PracownikDTO::getId).distinct().count();
    }
}
