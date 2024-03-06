package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.modelDTO.OcenaDTO;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.service.OcenaService;
import com.example.projektgruptest.service.WniosekService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OcenaController {
    private final OcenaService ocenaService;
    private final WniosekService wniosekService;
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Oceny/test")
    public String test() {
        String s="Oceny:\n";
        try {
            for(Ocena element : ocenaService.getOceny()) {
                s += element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nOceny pracownika 151:\n";
            for(Ocena element : ocenaService.getOcenyPracownika(151)) {
                s += element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nOcena 101:\n";
            s += ocenaService.getOcena(101) + "\n";
            s+="-".repeat(30) + "\n";

            ocenaService.addOcena(Ocena.builder()
                            .nazwa("pozytywna")
                            .data(new Date())
                            .iloscPunktow(70)
                    .build());
            ocenaService.deleteOcena(ocenaService.getOcena(101));

            s+="\nOceny po dodaniu i usunięciu oceny\n";
            for(Ocena element : ocenaService.getOceny()) {
                s += element + "\n";
            }
        }
        catch (EntityNotFoundException e) {
            s += "\nPróbowano znaleść obiek o indeksie którego nie ma w bazie danych\n";
        }

        return s;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Oceny")
    public List<OcenaDTO> getOceny(@AuthenticationPrincipal UserWithPracownik user){
        List<OcenaDTO> list = new ArrayList<>();
        for(Ocena o: ocenaService.getOcenyPracownika(user.getPracownik().getIdPracownika())){
            list.add(OcenaDTO.builder()
                    .idOceny(o.getIdOceny())
                    .nazwa(o.getNazwa())
                    .iloscPunktow(o.getIloscPunktow())
                    .data(o.getData())
                    .build());
        }
        return list;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/Ocena")
    public void dodajOcene(@RequestBody OcenaDTO ocenaDTO)
    {
        Ocena ocena = Ocena.builder()
                .nazwa(ocenaDTO.getNazwa())
                .iloscPunktow(ocenaDTO.getIloscPunktow())
                .data(ocenaDTO.getData())
                .wniosek(wniosekService.getWniosek(ocenaDTO.getIdWniosku()))
                .build();
        ocenaService.addOcena(ocena);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/Ocena/{id}")
    public void edytujOcene(@PathVariable Long id, @RequestBody OcenaDTO o, @AuthenticationPrincipal UserWithPracownik user) {
        Ocena ocena = ocenaService.getOcena(id);
        for(Ocena oc : ocenaService.getOcenyPracownika(user.getPracownik().getIdPracownika())){
            if(oc == ocena){
                ocena.setNazwa(o.getNazwa());
                ocena.setIloscPunktow(o.getIloscPunktow());
                ocena.setData(o.getData());
                ocenaService.addOcena(ocena);
                break;
            }
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/Ocena/{id}")
    public void usunOcene(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        Ocena ocena = ocenaService.getOcena(id);
       for (Ocena o: ocenaService.getOcenyPracownika(user.getPracownik().getIdPracownika())){
           if(o == ocena){
               ocenaService.deleteOcena((ocena));
               break;
           }
       }
    }
}
