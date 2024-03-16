package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.modelDTO.DodawanieOcenDTO;
import com.example.projektgruptest.modelDTO.OcenaDTO;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.service.OcenaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OcenaController {
    private final OcenaService ocenaService;
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/oceny")
    public List<OcenaDTO> getOceny(@AuthenticationPrincipal UserWithPracownik user){
        List<Ocena> listaOcenPracownika = ocenaService.getOcenyPracownika(user.getPracownik().getId());
        return ocenaService.convertListToDTO(listaOcenPracownika);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/osiagnieciaZOceny/{id}")
    public List<OsiagniecieDTO> getOsiagnieciaZOceny(@PathVariable long id, @AuthenticationPrincipal UserWithPracownik user) {
        return ocenaService.getOsiagnieciaZOcenyUsera(id,user.getPracownik().getId());
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/ocena")
    public ResponseEntity<String> dodajOcene(@RequestBody @Valid DodawanieOcenDTO dodawanieOcenDTO, BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Nieprawidłowe dane: " + result.getAllErrors());
        }
        ocenaService.addOceny(dodawanieOcenDTO);
        return ResponseEntity.ok("Sukces");
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/ocenaZatwierdz/{id}")
    public ResponseEntity<String> zatwierdzOcene(@PathVariable Long id) {
        if(ocenaService.czyZatwierdzona(id)) {
            return ResponseEntity.badRequest().body("Ocena jest już zatwierdzona, nie można jej modyfikować");
        }
        ocenaService.zatwierdzOcene(id);
        return ResponseEntity.ok("Sukces");
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/ocena/{id}")
    public ResponseEntity<String> usunOcene(@PathVariable Long id) {
       ocenaService.deleteOcena(id);
        return ResponseEntity.ok("Sukces");
    }
}
