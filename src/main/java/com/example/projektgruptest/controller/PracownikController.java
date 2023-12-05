package com.example.projektgruptest.controller;

import com.example.projektgruptest.model.*;
import com.example.projektgruptest.service.PracownikService;
import com.example.projektgruptest.service.PracownikStanowiskoService;
import com.example.projektgruptest.service.RodzajDzialanosciService;
import com.example.projektgruptest.service.StopienNaukowyService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PracownikController {

    private final PracownikService pracownikService;
    private final PracownikStanowiskoService pracownikStanowiskoService; //dla testow
    private final RodzajDzialanosciService rodzajDzialanosciService;
    private final StopienNaukowyService stopienNaukowyService;
    @GetMapping("/")
    public String getStopnieNaukowe() {
//        pracownikService.addPracownik(Pracownik.builder()
//                .pracownikStanowisko(pracownikService.getPracownikStanowisko().get(0))
//                .rodzajDzialalnosci(pracownikService.getRodzajeDzialalnosci().get(0))
//                .stopienNaukowy(pracownikService.getStopnieNaukowe().get(0))
//                .imie("ADAM")
//                .nazwisko("NOWAK")
//                .emailSluzbowy("adamNowak@pb.edu.pl")
//                .build());
        //pracownikService.deletePracownik(pracownikService.getPracownik(1));

        String s="Stopnie naukowe:\n";

        s+="\nPracownicy:\n";
//        for(Pracownik element : pracownikService.getPracownik()) {
//            s += element.getIdPracownika() + " " + element.getPracownikStanowisko().getNazwa()
//                    + " "  + element.getRodzajDzialalnosci().getNazwa() + " "  +
//                    element.getStopienNaukowy().getNazwa() + " "  +
//                    element.getImie() + " "  + element.getNazwisko() + " "  +
//                    element.getEmailSluzbowy() + " "  + "\n";
//        }
        return s;
    }
    @GetMapping("/Pracownik")
    public String test(){
        String s="Pracownicy: \n";
        try{
            for(Pracownik element: pracownikService.getPracownicy()){
                s+=element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nPracownicy ze stanowiska 1\n";
            for(Pracownik element: pracownikService.getPracownikStanowisko(1)){
                s+=element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nPracownicy z Stopien naukowy 1\n";
            for(Pracownik element: pracownikService.getPracownikStopienNaukowy(1)){
                s+=element + "\n";
            }
            s+="-".repeat(30) + "\n";

            s+="\nPracownicy z Rodzaj Dzialanosci 1\n";
            for(Pracownik element: pracownikService.getPracownikRodzajDzialanosci(1)){
                s+=element + "\n";
            }
            s+="-".repeat(30) + "\n";
            s+="\nPracownik 1\n";

            s+=pracownikService.getPracownik(1) + "\n";

            s+="-".repeat(30) + "\n";

            pracownikService.addPracownik(Pracownik.builder()
                    .pracownikStanowisko(pracownikStanowiskoService.getPracownicyStanowiska().get(0))
                    .rodzajDzialalnosci(rodzajDzialanosciService.getRodzajeDzialanosci().get(0))
                    .stopienNaukowy(stopienNaukowyService.getStopnieNaukowe().get(0))
                    .imie("ADAM")
                    .nazwisko("NOWAK")
                    .emailSluzbowy("adamNowak@pb.edu.pl")
                    .build());


            //pracownikService.deletePracownik(pracownikService.getPracownik(51));
            s+="\nPracownicy po dodaniu i usunieciu pracownika\n";
            for(Pracownik element: pracownikService.getPracownicy()){
                s+=element + "\n";
            }

        }
        catch (EntityNotFoundException e) {
            s += "\nPróbowano znaleść obiekt o indeksie którego nie ma w bazie danych\n";
        }
        return s;
    }
}
