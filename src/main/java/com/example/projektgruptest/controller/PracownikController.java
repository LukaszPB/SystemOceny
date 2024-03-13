package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.modelDTO.PracownikDTO;
import com.example.projektgruptest.service.PracownikService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PracownikController {
    private final PracownikService pracownikService;

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/pracownikPobierzWszystkich")
    public List<PracownikDTO> getPracownicy() {
        return pracownikService.convertListToDTO(pracownikService.getPracownicy());
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/pracownik")
    public PracownikDTO getPracownik(@AuthenticationPrincipal UserWithPracownik user) {
        return pracownikService.convertToDTO(
                pracownikService.getPracownik(user.getPracownik().getId()));
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/pracownicy_przelozonego")
    public List<PracownikDTO> getPracownicyPrzelozonego(@AuthenticationPrincipal UserWithPracownik user) {
        Pracownik pracownik = user.getPracownik();
        if(pracownik != null) {
            return pracownikService.convertListToDTO(
                    pracownikService.getPracownicyPrzelozonego(pracownik.getId()));
        }
        return null;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/pracownikPrzelozony")
    public PracownikDTO getPrzelozony(@AuthenticationPrincipal UserWithPracownik user) {
        Pracownik pracownik = user.getPracownik();
        if(pracownik != null) {
            return pracownikService.convertToDTO(
                    pracownikService.getPrzelozonego(pracownik.getId()));
        }
        return null;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/pracownikDodaj") //dtopracownik
    public void dodajPracownika(@RequestBody PracownikDTO pracownikDTO) {
        pracownikService.addPracownik(pracownikService.buildPracownik(pracownikDTO));
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/pracownikEdytuj/{id}")
    public void edytujPracownika(@PathVariable Long id, @RequestBody PracownikDTO pracownikDTO) {
        pracownikService.editPracownik(id,pracownikDTO);
    }
}
