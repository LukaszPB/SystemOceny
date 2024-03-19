package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.modelDTO.DodawanieOsiagniecDTO;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.service.OsiagniecieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OsiagniecieController {
    private final OsiagniecieService osiagniecieService;
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/osiagniecia")
    public List<OsiagniecieDTO> getOsiagniecia(@AuthenticationPrincipal UserWithPracownik user) {
        List<Osiagniecie> osiagniecieList =
                osiagniecieService.getOsiagnieciaPracownika(user.getPracownik().getId());

        return osiagniecieService.convertListToDTO(osiagniecieList);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/osiagnieciaZGrupy/{id}")
    public List<OsiagniecieDTO> getOsiagnieciaZGrupy(@PathVariable long id, @AuthenticationPrincipal UserWithPracownik user) {
        List<Osiagniecie> osiagnieciaPracownikaList =
                osiagniecieService.getOsiagnieciaZGrupy(id,user.getPracownik().getGrupa());

        return osiagniecieService.convertListToDTO(osiagnieciaPracownikaList);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/osiagniecie")
    public ResponseEntity<String> dodajOsiagniecie(@RequestBody @Valid DodawanieOsiagniecDTO dodawanieOsiagniecDTO,
                                                   BindingResult result, @AuthenticationPrincipal UserWithPracownik user) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Nieprawidłowe dane: " + result.getAllErrors());
        }
        osiagniecieService.addOsiagniecia(dodawanieOsiagniecDTO,user.getPracownik());
        return ResponseEntity.ok("Sukces");
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/osiagniecie/{id}")
    public ResponseEntity<String> edytujOsiagniecie(@PathVariable long id, @RequestBody @Valid OsiagniecieDTO osiagniecieDTO,
                                                    BindingResult result, @AuthenticationPrincipal UserWithPracownik user) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body("Nieprawidłowe dane: " + result.getAllErrors());
        }
        else if(osiagniecieService.canModifyOsiagniecie(user.getPracownik(),id)) {
            osiagniecieService.editOsiagniecie(osiagniecieDTO,id,user.getPracownik());
            return ResponseEntity.ok("Sukces");
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Brak uprawnień do edycji");
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/osiagniecieZatwierdz/{id}")
    public ResponseEntity<String> zatwierdzOsiagniecie(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        if(osiagniecieService.canApproveOsiagniecie(user.getPracownik(),id)) {
            osiagniecieService.approveOsiagniecie(id);
            return ResponseEntity.ok("Sukces");
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Brak uprawnień do zatwierdzania");
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/osiagniecie/{id}")
    public ResponseEntity<String> usunOsiagniecie(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        if(osiagniecieService.canModifyOsiagniecie(user.getPracownik(),id)) {
            osiagniecieService.deleteOsiagniecie(id);
            return ResponseEntity.ok("Sukces");
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Brak uprawnień do usuwania");
        }
    }
}
