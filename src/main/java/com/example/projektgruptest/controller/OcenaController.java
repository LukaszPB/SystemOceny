package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.modelDTO.OcenaDTO;
import com.example.projektgruptest.service.OcenaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OcenaController {
    private final OcenaService ocenaService;
    //private final WniosekService wniosekService;
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/oceny")
    public List<OcenaDTO> getOceny(@AuthenticationPrincipal UserWithPracownik user){
        List<OcenaDTO> list = new ArrayList<>();
        for(Ocena o: ocenaService.getOcenyPracownika(user.getPracownik().getId())){
            list.add(OcenaDTO.builder()
                    .id(o.getId())
                    .nazwa(o.getNazwa())
                    .dataPoczatkowa(o.getDataPoczatkowa())
                    .dataKoncowa(o.getDataKoncowa())
                    .build());
        }
        return list;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/ocena")
    public void dodajOcene(@RequestBody OcenaDTO ocenaDTO)
    {
        Ocena ocena = Ocena.builder()
                .nazwa(ocenaDTO.getNazwa())
                .build();
        ocenaService.addOcena(ocena);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/ocena/{id}")
    public void edytujOcene(@PathVariable Long id, @RequestBody OcenaDTO o, @AuthenticationPrincipal UserWithPracownik user) {
        Ocena ocena = ocenaService.getOcena(id);
        for(Ocena oc : ocenaService.getOcenyPracownika(user.getPracownik().getId())){
            if(oc == ocena){
                ocena.setNazwa(o.getNazwa());
                ocenaService.addOcena(ocena);
                break;
            }
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/ocena/{id}")
    public void usunOcene(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        Ocena ocena = ocenaService.getOcena(id);
       for (Ocena o: ocenaService.getOcenyPracownika(user.getPracownik().getId())){
           if(o == ocena){
               ocenaService.deleteOcena((ocena));
               break;
           }
       }
    }
}
