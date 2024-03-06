package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.modelDTO.KryteriaOcenyDTO;
import com.example.projektgruptest.service.KryteriaOcenyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KryteriaOcenyController {
    private final KryteriaOcenyService kryteriaOcenyService;

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/KryteriaOcenyWszystkie")
    public List<KryteriaOcenyDTO> getKryteriaOceny() {
        return kryteriaOcenyService.getKryteriaOceny();
    }

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/KryteriumOceny")
    public KryteriaOcenyDTO getKryteriumOceny(@AuthenticationPrincipal UserWithPracownik user) {
        if(user.getPracownik() != null) {
            return kryteriaOcenyService.getKryteriumOceny(user.getPracownik());
        }
        return null;
    }

}
