package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.PracownikDTO;
import com.example.projektgruptest.service.PracownikService;
import com.example.projektgruptest.service.PracownikStanowiskoService;
import com.example.projektgruptest.service.RodzajDzialanosciService;
import com.example.projektgruptest.service.StopienNaukowyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PracownikController {
    private final PracownikService pracownikService;
    private final PracownikStanowiskoService pracownikStanowiskoService; //dla testow
    private final RodzajDzialanosciService rodzajDzialanosciService;
    private final StopienNaukowyService stopienNaukowyService;

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/PracownikPobierzWszystkich")
    public List<PracownikDTO> getPracownicy() {
        return pracownikService.getPracownicy();
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Pracownik")
    public PracownikDTO getPracownik(@AuthenticationPrincipal UserWithPracownik user) {
        return pracownikService.convertToDTO(
                pracownikService.getPracownik(user.getPracownik().getIdPracownika()));
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/PracownicyPrzelozonego")
    public List<PracownikDTO> getPracownicyPrzelozonego(@AuthenticationPrincipal UserWithPracownik user) {
        Pracownik pracownik = user.getPracownik();
        if(pracownik != null) {
            return pracownikService.getPracownicyPrzelozonego(pracownik.getIdPracownika());
        }
        return null;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/PracownikPrzelozony")
    public PracownikDTO getPrzelozony(@AuthenticationPrincipal UserWithPracownik user) {
        Pracownik pracownik = user.getPracownik();
        if(pracownik != null) {
            return pracownikService.getPrzelozonego(pracownik.getIdPracownika());
        }
        return null;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/PracownikDodaj")
    public void dodajPracownika(@RequestBody PracownikDTO pracownikDTO) {
        pracownikService.addPracownik(pracownikService.buildPracownik(pracownikDTO));
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/PracownikEdytuj/{id}")
    public void edytujPracownika(@PathVariable Long id, @RequestBody PracownikDTO pracownikDTO) {
        pracownikService.editPracownik(id,pracownikDTO);
    }
}
