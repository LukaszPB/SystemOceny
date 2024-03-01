package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.exception.PermissionDeniedException;
import com.example.projektgruptest.exception.ValidationFailedException;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.service.OsiagniecieService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
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
    public void dodajOsiagniecie(@RequestBody @Valid OsiagniecieDTO osiagniecieDTO, BindingResult result) {
        if(result.hasErrors()) {
            throw new ValidationFailedException("Validation has failed " + result.getFieldErrors());
        }
        osiagniecieService.addOsiagniecie(osiagniecieDTO);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/Osiagniecie/{id}")
    public void edytujOsiagniecie(@PathVariable long id, @RequestBody @Valid OsiagniecieDTO osiagniecieDTO, BindingResult result,
                                  @AuthenticationPrincipal UserWithPracownik user) {
        if(result.hasErrors()) {
            throw new ValidationFailedException("Validation has failed " + result.getFieldErrors());
        }
        else if(osiagniecieService.canApprove(user.getPracownik().getIdPracownika(),id)) {
            osiagniecieService.editOsiagnieciePrzelozony(osiagniecieDTO,id);
        }
        else if(osiagniecieService.canModify(user.getPracownik().getIdPracownika(),id)) {
            osiagniecieService.editOsiagnieciePracownik(osiagniecieDTO,id);
        }
        else {
            throw new PermissionDeniedException("You don't have permission to modify this achievement");
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/OsiagniecieZatwierdz/{id}")
    public void edytujOsiagniecie(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        if(osiagniecieService.canApprove(user.getPracownik().getIdPracownika(),id)) {
            osiagniecieService.approveOsiagniecie(id);
        }
        else {
            throw new PermissionDeniedException("You don't have permission to approve this achievement");
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/Osiagniecie/{id}")
    public void usunPracownika(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        if(osiagniecieService.canModify(user.getPracownik().getIdPracownika(),id)) {
            osiagniecieService.deleteOsiagniecie(id);
        }
        else {
            throw new PermissionDeniedException("You don't have permission to delete this achievement");
        }
    }
}
