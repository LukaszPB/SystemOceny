package com.example.projektgruptest.controller;


import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.model.auth.Uzytkownik;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.modelDTO.UzytkownikDTO;
import com.example.projektgruptest.service.RolaService;
import com.example.projektgruptest.service.UzytkownikService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UzytkownikController {
    private final RolaService rolaService;
    private final UzytkownikService uzytkownikService;
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Uzytkownicy")
    public List<UzytkownikDTO> getUzytkownicy(@AuthenticationPrincipal UserWithPracownik user)
    {
        List<UzytkownikDTO> list = new ArrayList<>();
        if (user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Pobierz nazwę roli
                .anyMatch(role -> role.equals(rolaService.getRola(1).getNazwa()))) {
           for(Uzytkownik u: uzytkownikService.getUzytkownicy()){
               if(u.getPracownik()!=null){
                   list.add(uzytkownikService.addUzytkownikDTO(u));
               }
               else
               {
                   list.add(uzytkownikService.addUzytkownikDTOBezPracownika(u));
               }
           }
        }
        return list;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/Uzytkownik")
    public void dodajUzytkownika(@RequestBody UzytkownikDTO uzytkownikDTO) {

        Uzytkownik uzytkownik = uzytkownikService.addUzytkownik(uzytkownikDTO);
        uzytkownikService.addUzytkownik(uzytkownik);
    }


}
