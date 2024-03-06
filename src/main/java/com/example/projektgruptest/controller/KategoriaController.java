package com.example.projektgruptest.controller;

import com.example.projektgruptest.model.KategoriaOsiagniec;
import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.modelDTO.KryteriaOcenyDTO;
import com.example.projektgruptest.service.KategoriaService;
import com.example.projektgruptest.service.KryteriaOcenyService;
import com.example.projektgruptest.service.PodKategorieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class KategoriaController {

    private final KategoriaService kategoriaService;
    private final PodKategorieService podKategorieService;

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/kategoriaWszystkie")
    public List<KategoriaOsiagniec> getListaKategorii(){
        return kategoriaService.getKategorie();
    }

    //TODO: Adrian do zrobienia jutro 07.03.24
//    @SecurityRequirement(name = "JWT Authentication")
//    @GetMapping("/podKategoria")
//    public Set<PodKategoria> getPodKategoria(@RequestBody String nazwaKategorii){
//        return kategoriaService.getPodKategoriaSet(nazwaKategorii);
//    }

}
