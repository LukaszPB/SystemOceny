package com.example.projektgruptest.validator;

import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.service.PodKategorieService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class IloscPunktowValidator implements ConstraintValidator<ValidIloscPunktow, OsiagniecieDTO> {

    @Autowired
    private PodKategorieService podKategorieService;
    @Override
    public boolean isValid(OsiagniecieDTO osiagniecieDTO, ConstraintValidatorContext context) {
        if (osiagniecieDTO == null) {
            return false;
        }

        PodKategoria podkategoria = podKategorieService.getPodkategoria(osiagniecieDTO.getPodKategoriaNazwa());
        if (podkategoria == null) {
            return false;
        }

        int min = podkategoria.getMinPunktow();
        int max = podkategoria.getMaxPunktow();

        int iloscPunktow = osiagniecieDTO.getIloscPunktow();
        System.out.println(iloscPunktow + " " + min + " "  + max);
        return iloscPunktow >= min && iloscPunktow <= max;
    }
}