package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.OkresRozliczeniowy;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.PracownikDTO;
import com.example.projektgruptest.model.Wniosek;
import com.example.projektgruptest.modelDTO.WniosekDTO;
import com.example.projektgruptest.service.OcenaService;
import com.example.projektgruptest.service.OkresRozliczeniowyService;
import com.example.projektgruptest.service.PracownikService;
import com.example.projektgruptest.service.WniosekService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WniosekController {
    private final WniosekService wniosekService;
    private final PracownikService pracownikService; // Potrzebne tylko do testów
    private final OcenaService ocenaService;
    private final OkresRozliczeniowyService okresRozliczeniowyService; // Potrzebne tylko do testów

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Wnioski")
    public List<WniosekDTO> getWnioski(@AuthenticationPrincipal UserWithPracownik user){

        List<Wniosek> wnioskiList =
                wniosekService.getWnioskiPracownika(user.getPracownik().getIdPracownika());

        return wniosekService.convertListToDTO(wnioskiList);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/Wniosek")
    public void dodajWniosek(@RequestBody WniosekDTO wniosekDTO) {
        wniosekService.addWniosek(wniosekDTO);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/Wniosek/{id}")
    public void usunWniosek(@PathVariable Long id,@AuthenticationPrincipal UserWithPracownik user) {
        Wniosek wniosek = wniosekService.getWniosek(id);
        wniosekService.deleteWniosek(wniosek);
    }

}
