package com.example.projektgruptest.controller;

import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.service.OcenaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class OcenaController {
    private final OcenaService ocenaService;
    @GetMapping("Ocena")
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
}
