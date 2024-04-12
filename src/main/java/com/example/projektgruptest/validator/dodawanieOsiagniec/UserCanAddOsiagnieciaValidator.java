package com.example.projektgruptest.validator.dodawanieOsiagniec;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.modelDTO.DodawanieOsiagniecDTO;
import com.example.projektgruptest.service.PodKategorieService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

public class UserCanAddOsiagnieciaValidator implements ConstraintValidator<ValidUserCanAddOsiagniecia, DodawanieOsiagniecDTO> {
    @Autowired
    private PodKategorieService podKategorieService;
    @Override
    public boolean isValid(DodawanieOsiagniecDTO dodawanieOsiagniecDTO, ConstraintValidatorContext context) {
        UserWithPracownik user = getUser();
        if(user == null) {
            return false;
        }
        PodKategoria podKategoria = podKategorieService.getPodkategoria(dodawanieOsiagniecDTO.getPodKategoria());
        if(podKategoria == null) {
            return false;
        }
        if(Objects.equals(user.getPracownik().getGrupa().getId(),podKategoria.getGrupa().getId())) {
            return true;
        }

        return dodawanieOsiagniecDTO.getPracownikIdList().stream().allMatch(
                id -> Objects.equals(user.getPracownik().getId(), id));
    }
    private UserWithPracownik getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof UserWithPracownik) {
            return (UserWithPracownik) userDetails;
        }
        return null;
    }
}
