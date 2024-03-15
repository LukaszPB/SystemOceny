package com.example.projektgruptest.controller;


import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.auth.Uzytkownik;
import com.example.projektgruptest.modelDTO.UzytkownikDTO;
import com.example.projektgruptest.repo.RolaRepo;
import com.example.projektgruptest.service.RolaService;
import com.example.projektgruptest.service.UzytkownikService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
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
    public List<UzytkownikDTO> getUzytkownicy()
    {
        List<Uzytkownik> uzytkownikList = uzytkownikService.getUzytkownicy();

        return uzytkownikService.convertListToDTO(uzytkownikList);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/uzytkownik")
    public ResponseEntity<String> dodajUzytkownika(@RequestBody @Valid UzytkownikDTO uzytkownikDTO, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Nieprawidłowe dane: " + result.getAllErrors());
        }
        uzytkownikService.addUzytkownik(uzytkownikDTO);
        return ResponseEntity.ok("Sukces");
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/uzytkownikEdytuj/{id}")
    public ResponseEntity<String> edytujUzytkownika(@PathVariable Long id,@RequestBody @Valid UzytkownikDTO uzytkownikDTO, BindingResult result)
    {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Nieprawidłowe dane: " + result.getAllErrors());
        }
        uzytkownikService.editUzytkownik(uzytkownikDTO,id);
        return ResponseEntity.ok("Sukces");
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/uzytkownikUsun/{id}")
    public void usunUzytkownika(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user)
    {
        //dodac obsluge jak sie opoda zle id
        Uzytkownik uzytkownik = uzytkownikService.getUzytkownik(id);
        uzytkownikService.deleteUzytkownik(uzytkownik);

    }


}
