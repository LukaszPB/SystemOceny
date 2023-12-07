package com.example.projektgruptest.controller;

import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.PracownikDTO;
import com.example.projektgruptest.service.PracownikService;
import com.example.projektgruptest.service.PracownikStanowiskoService;
import com.example.projektgruptest.service.RodzajDzialanosciService;
import com.example.projektgruptest.service.StopienNaukowyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PracownikController {
    private final PracownikService pracownikService;
    private final PracownikStanowiskoService pracownikStanowiskoService; //dla testow
    private final RodzajDzialanosciService rodzajDzialanosciService;
    private final StopienNaukowyService stopienNaukowyService;
    @GetMapping("/Pracownik/test")
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
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/pracownik_getAll")
    public List<PracownikDTO> getPracownicy() {
        List<PracownikDTO> list = new ArrayList<>();
        for(Pracownik p : pracownikService.getPracownicy()) {
            list.add(PracownikDTO.builder()
                    .idPracownika(p.getIdPracownika())
                    .imie(p.getImie())
                    .nazwisko(p.getNazwisko())
                    .emailSluzbowy(p.getEmailSluzbowy())
                    .rodzajDzialalnosciNazwa(p.getRodzajDzialalnosci().getNazwa())
                    .stanowiskoNazwa(p.getPracownikStanowisko().getNazwa())
                    .stopienNaukowyNazwa(p.getStopienNaukowy().getNazwa())
                    .build());
        }
        return list;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/pracownik_add")
    public void dodajPracownika(@RequestBody PracownikDTO pracownikDTO) {
        pracownikService.addPracownik(Pracownik.builder()
                .imie(pracownikDTO.getImie())
                .nazwisko(pracownikDTO.getNazwisko())
                .emailSluzbowy(pracownikDTO.getEmailSluzbowy())
                .stopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowyNazwa()))
                .pracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowiskoNazwa()))
                .rodzajDzialalnosci(rodzajDzialanosciService.getRodzajDzialanosci(pracownikDTO.getRodzajDzialalnosciNazwa()))
                .build());
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/pracownik_edit/{id}")
    public void edytujPracownika(@PathVariable Long id, @RequestBody PracownikDTO pracownikDTO) {
        Pracownik pracownik = pracownikService.getPracownik(id);
        pracownik.setImie(pracownikDTO.getImie());
        pracownik.setNazwisko(pracownikDTO.getNazwisko());
        pracownik.setEmailSluzbowy(pracownikDTO.getEmailSluzbowy());
        pracownik.setStopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowyNazwa()));
        pracownik.setPracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowiskoNazwa()));
        pracownik.setRodzajDzialalnosci(rodzajDzialanosciService.getRodzajDzialanosci(pracownikDTO.getRodzajDzialalnosciNazwa()));
        pracownikService.addPracownik(pracownik);
    }

    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/pracownik_delete/{id}")
    public void deletePracownik(@PathVariable Long id) {
        pracownikService.deletePracownik(pracownikService.getPracownik(id));
    }
}
