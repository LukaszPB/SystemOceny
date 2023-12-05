package com.example.projektgruptest.controller;

import com.example.projektgruptest.model.Wniosek;
import com.example.projektgruptest.service.OkresRozliczeniowyService;
import com.example.projektgruptest.service.PracownikService;
import com.example.projektgruptest.service.WniosekService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WniosekController {
    private final WniosekService wniosekService;
    private final PracownikService pracownikService; // Potrzebne tylko do testów
    private final OkresRozliczeniowyService okresRozliczeniowyService; // Potrzebne tylko do testów
    @GetMapping("Wniosek")
    public String test() {
        String s="Wnioski:\n";
        try {
            for(Wniosek element : wniosekService.getWnioski()) {
                s += element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nWnioski pracownika 151:\n";
            for(Wniosek element : wniosekService.getWnioskiPraconika(151)) {
                s += element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nWniosek 101:\n";
            s += wniosekService.getWniosek(101) + "\n";
            s+="-".repeat(30) + "\n";

            wniosekService.addWniosek(Wniosek.builder()
                    .pracownik(pracownikService.getPracownik(1))
                    .okresRozliczeniowy(okresRozliczeniowyService.getOkresRozliczeniowy(1))
                    .build());
            wniosekService.deleteWniosek(wniosekService.getWniosek(1));

            s+="\nWnioski po dodaniu i usunięciu wniosku\n";
            for(Wniosek element : wniosekService.getWnioski()) {
                s += element + "\n";
            }
        }
        catch (EntityNotFoundException e) {
            s += "\nPróbowano znaleść obiek o indeksie którego nie ma w bazie danych\n";
        }

        return s;
    }
}
