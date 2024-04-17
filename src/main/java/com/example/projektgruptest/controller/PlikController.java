package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.service.OsiagniecieService;
import com.example.projektgruptest.service.PlikService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PlikController {
    private final PlikService plikService;
    private final OsiagniecieService osiagniecieService;
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping(path = "/dodajPlik/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> dodajPlik(@RequestPart MultipartFile plik, @PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) throws IOException {
        if(osiagniecieService.canModifyOsiagniecie(user.getPracownik(),id)) {
            plikService.dodajPlik(plik,osiagniecieService.getOsiagniecie(id));
            return ResponseEntity.ok("Sukces");
        }
        return ResponseEntity.badRequest().body("Użytkownik nie ma uprawnień do modyfikowania osiągnięcia: " + id);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping( "/pobierzPlik/{id}")
    public List<byte[]> pobierzPliki(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) throws IOException {
        if(osiagniecieService.canSeeOsiagniecie(user.getPracownik(),id)) {
            return plikService.getPliki(osiagniecieService.getOsiagniecie(id));
        }
        return null;
    }
}
