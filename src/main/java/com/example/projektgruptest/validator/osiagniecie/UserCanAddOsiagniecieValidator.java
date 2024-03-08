package com.example.projektgruptest.validator.osiagniecie;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.service.PodKategorieService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

public class UserCanAddOsiagniecieValidator implements ConstraintValidator<ValidUserCanAddOsiagniecie, OsiagniecieDTO> {
    @Autowired
    private PodKategorieService podKategorieService;
    @Override
    public boolean isValid(OsiagniecieDTO osiagniecieDTO, ConstraintValidatorContext context) {
        UserWithPracownik user = getUser();
        if(user == null) {
            return false;
        }
        PodKategoria podKategoria = podKategorieService.getPodkategoria(osiagniecieDTO.getPodKategoriaNazwa());
        if(podKategoria == null) {
            return false;
        }

        return Objects.equals(user.getPracownik().getId(), osiagniecieDTO.getIdPracownika()) ||
                Objects.equals(user.getPracownik().getGrupa().getId(),podKategoria.getGrupa().getId());
    }
    private UserWithPracownik getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserWithPracownik) {
            return (UserWithPracownik) userDetails;
        }
        return null;
    }
}
