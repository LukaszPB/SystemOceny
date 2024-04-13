package com.example.projektgruptest.controller;


import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.modelDTO.PodKategoriaDTO;
import com.example.projektgruptest.service.PodKategorieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PodKategoriaController {
    private final PodKategorieService podKategorieService;


    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/podkategoriaWszystkie")
    public List<PodKategoriaDTO> getWszystkiePodKategorie() {
        return podKategorieService.convertListToDTO(podKategorieService.getPodKategorie());
    }

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/podkategoria/{id}")
    public PodKategoriaDTO getPodKategoriaDTO(@PathVariable long id) {
        return podKategorieService.convertToDTO(podKategorieService.getPodkategoria(id));
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/podkategoriaGrupa/{nazwaGrupy}")
    public List<PodKategoriaDTO> getPodKategoriaByGrupa(@PathVariable String nazwaGrupy) {
        return podKategorieService.convertListToDTO(podKategorieService.getPodkategorieByGrupa(nazwaGrupy));
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/podkategoriaGrupaUsera")
    public List<PodKategoriaDTO> getPodKategoriaByGrupaUsera(@AuthenticationPrincipal UserWithPracownik user) {
        if(user.getPracownik()!=null)
        {
            return podKategorieService.convertListToDTO(podKategorieService.getPodkategorieByGrupa(user.getPracownik().getGrupa().getNazwa()));
        }
        return null;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/dodaj")
    public ResponseEntity<String> dodajPodkategorie(@RequestBody PodKategoriaDTO podKategoriaDTO) {
        podKategorieService.addPodkategoria(podKategoriaDTO);
        return ResponseEntity.ok("Podkategoria dodana pomyślnie");
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/zarchiwizuj")
    public ResponseEntity<String> zarchiwizujPodkategorie(@RequestBody Date date) {
        podKategorieService.zarchiwizuj(date);
        return ResponseEntity.ok("Podkategorie zostały pomyślnie zarchiwizowane");
    }
}
