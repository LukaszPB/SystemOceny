package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.service.OsiagniecieService;
import com.example.projektgruptest.service.PodKategorieService;
import com.example.projektgruptest.service.WniosekService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OsiagniecieController {
    private final OsiagniecieService osiagniecieService;
    private final WniosekService wniosekService; //Potrzebny tylko do testów
    private final PodKategorieService podKategorieService; //Potrzebny tylko do testów
    @GetMapping("/Osiagniecie")
    public String test() {
        String s="Osiągnięcia:\n";
        try {
            for(Osiagniecie element : osiagniecieService.getOsiagniecia()) {
                s += element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nOsiagnięcia z wniosku 51:\n";
            for(Osiagniecie element : osiagniecieService.getOsiagnieciaWniosku(51)) {
                s += element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nOsiagnięcia praconika 1:\n";
            for(Osiagniecie element : osiagniecieService.getOsiagnieciaPracownika(1)) {
                s += element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nOsiagnięcie 101:\n";
            s += osiagniecieService.getOsiagniecie(101) + "\n";
            s+="-".repeat(30) + "\n";

            osiagniecieService.addOsiagniecie(Osiagniecie.builder()
                    .czyZatwierdzone(false)
                    .data(new Date())
                    .nazwa("Publikacja naukowa")
                    .iloscPunktow(20)
                    .wniosek(wniosekService.getWniosek(1))
                    .podKategoria(podKategorieService.getPodKategorie().get(0))
                    .build());
            osiagniecieService.deleteOsiagniecie(osiagniecieService.getOsiagniecie(151));

            s+="\nOsiągnięcia po dodaniu i usunięciu osiągnięcia:\n";
            for(Osiagniecie element : osiagniecieService.getOsiagniecia()) {
                s += element + "\n";
            }
        }
        catch (EntityNotFoundException e) {
            s += "\nPróbowano znaleść obiek o indeksie którego nie ma w bazie danych\n";
        }

        return s;
    }

    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/osiagniecia")
    public List<Osiagniecie> getOsiagniecia(@AuthenticationPrincipal UserWithPracownik user) {
        return osiagniecieService.getOsiagnieciaPracownika(user.getPracownik().getIdPracownika());
    }
}
