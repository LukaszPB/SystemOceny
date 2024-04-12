package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
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
    @GetMapping("/pracownik")
    public PracownikDTO getPracownik(@AuthenticationPrincipal UserWithPracownik user) {
        return pracownikService.convertToDTO(
                pracownikService.getPracownik(user.getPracownik().getId()));
    }

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/pracownikByWydzial/{nazwaWydzialu}")
    public List<PracownikDTO> getPracownikbyWydzial(@PathVariable String nazwaWydzialu) {
        return pracownikService.convertListToDTO(pracownikService.getPracownikWydzial(nazwaWydzialu));
    }

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/pracownikByKatedra/{nazwaKatedry}")
    public List<PracownikDTO> getPracownikbyKatedra(@PathVariable String nazwaKatedry) {
        return pracownikService.convertListToDTO(pracownikService.getPracownikKatedra(nazwaKatedry));
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/pracownikZaktualizuj") //dtopracownik
    public void zaktualizujPracownika(@RequestBody PracownikDTO pracownikDTO) {
        pracownikService.addPracownik(pracownikDTO);
    }
}
