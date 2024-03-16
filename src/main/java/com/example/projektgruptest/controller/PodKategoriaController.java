package com.example.projektgruptest.controller;


import com.example.projektgruptest.modelDTO.PodKategoriaDTO;
import com.example.projektgruptest.service.PodKategorieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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



}
