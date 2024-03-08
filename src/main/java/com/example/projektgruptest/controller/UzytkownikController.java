package com.example.projektgruptest.controller;


import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.auth.Uzytkownik;
import com.example.projektgruptest.modelDTO.UzytkownikDTO;
import com.example.projektgruptest.repo.RolaRepo;
import com.example.projektgruptest.service.RolaService;
import com.example.projektgruptest.service.UzytkownikService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UzytkownikController {
    private final RolaService rolaService;
    private final RolaRepo rolaRepo;
    private final UzytkownikService uzytkownikService;
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/uzytkownicy")
    public List<UzytkownikDTO> getUzytkownicy(@AuthenticationPrincipal UserWithPracownik user)
    {
        List<UzytkownikDTO> list = new ArrayList<>();
        if (user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
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
    @PostMapping("/uzytkownik")
    public void dodajUzytkownika(@RequestBody UzytkownikDTO uzytkownikDTO,@AuthenticationPrincipal UserWithPracownik user) {
        if (user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals(rolaService.getRola(1).getNazwa()))) {
            Uzytkownik uzytkownik = uzytkownikService.addUzytkownik(uzytkownikDTO);
            uzytkownikService.addUzytkownik(uzytkownik);
        }

    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/uzytkownik/{id}")
    public void edytujUzytkownika(@PathVariable Long id,@RequestBody UzytkownikDTO u,@AuthenticationPrincipal UserWithPracownik user)
    {
        Uzytkownik uzytkownik = uzytkownikService.getUzytkownik(id);
        if (user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals(rolaService.getRola(1).getNazwa()))) {
            System.out.println("ADMIN");
            if(rolaService.getRola(u.getRola())!=null)
            {
                uzytkownik.setRola(rolaService.getRola(u.getRola()));
            }
            uzytkownikService.addUzytkownik(uzytkownik);
        }
        if(user.getPracownik()!=null)
        {
            System.out.println("WSZEDLEM");
            System.out.println(user.getPracownik().getId());
            if(uzytkownikService.getUzytkownik(id).getPracownik().getId() == user.getPracownik().getId())
            {
                System.out.println("WSZEDLEM v2");
                uzytkownik.setLogin(u.getLogin());
                uzytkownik.setHaslo(u.getHaslo());
                uzytkownikService.addUzytkownik(uzytkownik);
            }
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/uzytkownik/{id}")
    public void usunUzytkownika(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user)
    {
        Uzytkownik uzytkownik = uzytkownikService.getUzytkownik(id);
        if (user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals(rolaService.getRola(1).getNazwa()))) {
           uzytkownikService.deleteUzytkownik(uzytkownik);
        }
    }


}
