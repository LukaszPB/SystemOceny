package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.service.OsiagniecieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OsiagniecieController {
    private final OsiagniecieService osiagniecieService;
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Osiagniecia")
    public List<OsiagniecieDTO> getOsiagniecia(@AuthenticationPrincipal UserWithPracownik user) {
        List<Osiagniecie> osiagniecieList =
                osiagniecieService.getOsiagnieciaPracownika(user.getPracownik().getIdPracownika());

        return osiagniecieService.convertListToDTO(osiagniecieList);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/OsiagnieciaPodwladnych")
    public List<OsiagniecieDTO> getOsiagnieciaPodwladnych(@AuthenticationPrincipal UserWithPracownik user) {
        List<Osiagniecie> osiagnieciaPodwladnychList =
                osiagniecieService.getOsiagnieciaPodwladnych(user.getPracownik().getIdPracownika());

        return osiagniecieService.convertListToDTO(osiagnieciaPodwladnychList);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/Osiagniecie")
    public void dodajOsiagniecie(@RequestBody OsiagniecieDTO osiagniecieDTO) {
        //walidacja
        osiagniecieService.addOsiagniecie(osiagniecieDTO);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/Osiagniecie/{id}")
    public void edytujOsiagniecie(@PathVariable long id, @RequestBody OsiagniecieDTO osiagniecieDTO, @AuthenticationPrincipal UserWithPracownik user) {
        if(osiagniecieService.canModify(user.getPracownik().getIdPracownika(),id)) {
            osiagniecieService.editOsiagniecie(osiagniecieDTO,id);
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/OsiagniecieZatwierdz/{id}")
    public void edytujOsiagniecie(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        if(osiagniecieService.canApprove(user.getPracownik().getIdPracownika(),id)) {
            osiagniecieService.approveOsiagniecie(id);
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/Osiagniecie/{id}")
    public void usunPracownika(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        if(osiagniecieService.canModify(user.getPracownik().getIdPracownika(),id)) {
            osiagniecieService.deleteOsiagniecie(id);
        }
    }
}
