package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.exception.PermissionDeniedException;
import com.example.projektgruptest.model.Wniosek;
import com.example.projektgruptest.modelDTO.WniosekDTO;
import com.example.projektgruptest.service.PracownikService;
import com.example.projektgruptest.service.WniosekService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WniosekController {
    private final WniosekService wniosekService;
    private final PracownikService pracownikService;

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/aktywnyWniosek")
    public WniosekDTO getAktywnyWniosek(@AuthenticationPrincipal UserWithPracownik user){
        Wniosek wniosek =
                wniosekService.getAktywnyWniosekPracownika(user.getPracownik().getIdPracownika());

        return wniosekService.convertToDTO(wniosek);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/nieaktywneWnioski")
    public List<WniosekDTO> getNieaktywneWnioski(@AuthenticationPrincipal UserWithPracownik user){
        List<Wniosek> wnioskiList =
                wniosekService.getNieaktywneWnioskiPracownika(user.getPracownik().getIdPracownika());

        return wniosekService.convertListToDTO(wnioskiList);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/aktywnyWniosek/{id}")
    public WniosekDTO getAktywnyWniosekPoId(@AuthenticationPrincipal UserWithPracownik user,
                                                  @PathVariable Long id){
        if(pracownikService.CanUserAccessPracownikData(user.getPracownik().getIdPracownika(),id)) {
            Wniosek wniosek =
                    wniosekService.getAktywnyWniosekPracownika(id);

            return wniosekService.convertToDTO(wniosek);
        }
        else {
            throw new PermissionDeniedException("You don't have permission to get this Wniosek");
        }

    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/nieaktywneWnioski/{id}")
    public List<WniosekDTO> getNieaktywneWnioskiPoId(@AuthenticationPrincipal UserWithPracownik user,
                                                     @PathVariable Long id){
        if(pracownikService.CanUserAccessPracownikData(user.getPracownik().getIdPracownika(),id)) {
            List<Wniosek> wnioskiList =
                    wniosekService.getNieaktywneWnioskiPracownika(id);

            return wniosekService.convertListToDTO(wnioskiList);
        }
        else {
            throw new PermissionDeniedException("You don't have permission to get this Wniosek");
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/Wniosek")
    public void dodajWniosek(@RequestBody WniosekDTO wniosekDTO) {
        wniosekService.addWniosek(wniosekDTO);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/Wniosek/{id}")
    public void usunWniosek(@PathVariable Long id) {
        Wniosek wniosek = wniosekService.getWniosek(id);
        wniosekService.deleteWniosek(wniosek);
    }

}
