package com.example.projektgruptest.validator.dodawanieOsiagniec;

import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.modelDTO.DodawanieOsiagniecDTO;
import com.example.projektgruptest.service.PodKategorieService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class IloscPunktowDValidator implements ConstraintValidator<ValidIloscPunktowD, DodawanieOsiagniecDTO> {

    @Autowired
    private PodKategorieService podKategorieService;
    @Override
    public boolean isValid(DodawanieOsiagniecDTO dodawanieOsiagniecDTO, ConstraintValidatorContext context) {
        if (dodawanieOsiagniecDTO == null) {
            return false;
        }

        PodKategoria podkategoria = podKategorieService.getPodkategoria(dodawanieOsiagniecDTO.getPodKategoria());
        if (podkategoria == null) {
            return false;
        }

        int min = podkategoria.getMinPunktow();
        int max = podkategoria.getMaxPunktow();

        int iloscPunktow = dodawanieOsiagniecDTO.getIloscPunktow();
        return iloscPunktow >= min && iloscPunktow <= max;
    }
}