package com.example.projektgruptest.validator;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Pracownik;
//import com.example.projektgruptest.service.WniosekService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Objects;

public class UserCanAddToWniosekValidator implements ConstraintValidator<ValidUserCanAddToWnisek, Long> {
//    @Autowired
//    private WniosekService wniosekService;

    @Override
    public boolean isValid(Long idWniosku, ConstraintValidatorContext constraintValidatorContext) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Pracownik user = getUser(userDetails).getPracownik();
//        Wniosek wniosek = wniosekService.getWniosek(idWniosku);
//
//        return Objects.equals(wniosek.getPracownik().getIdPracownika(), user.getIdPracownika())
//                && wniosek.getOcena() == null;
        return true;
    }
    private UserWithPracownik getUser(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails instanceof UserWithPracownik) {
            return (UserWithPracownik) userDetails;
        }
        return null;
    }
}
