package com.example.projektgruptest.controller;

import com.example.projektgruptest.model.*;
import com.example.projektgruptest.service.PracownikService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PracownikController {

    private final PracownikService pracownikService;
    @GetMapping("/")
    public String getStopnieNaukowe() {
        pracownikService.addPracownik(Pracownik.builder()
                .pracownikStanowisko(pracownikService.getPracownikStanowisko().get(0))
                .rodzajDzialalnosci(pracownikService.getRodzajeDzialalnosci().get(0))
                .stopienNaukowy(pracownikService.getStopnieNaukowe().get(0))
                .imie("ADAM")
                .nazwisko("NOWAK")
                .emailSluzbowy("adamNowak@pb.edu.pl")
                .build());
        //pracownikService.deletePracownik(pracownikService.getPracownik(1));

        String s="Stopnie naukowe:\n";
        for(StopienNaukowy element : pracownikService.getStopnieNaukowe()) {
            s += element.getIdStopniaNaukowego() + " " + element.getNazwa() + "\n";
        }
        s+="\nRodzaje działalności:\n";
        for(RodzajDzialalnosci element : pracownikService.getRodzajeDzialalnosci()) {
            s += element.getIdRodzajDzialalnosci() + " " + element.getNazwa() + "\n";
        }
        s+="\nStanowiska:\n";
        for(PracownikStanowisko element : pracownikService.getPracownikStanowisko()) {
            s += element.getIdStanowiska() + " " + element.getNazwa() + "\n";
        }
        s+="\nPracownicy:\n";
        for(Pracownik element : pracownikService.getPracownik()) {
            s += element.getIdPracownika() + " " + element.getPracownikStanowisko().getNazwa()
                    + " "  + element.getRodzajDzialalnosci().getNazwa() + " "  +
                    element.getStopienNaukowy().getNazwa() + " "  +
                    element.getImie() + " "  + element.getNazwisko() + " "  +
                    element.getEmailSluzbowy() + " "  + "\n";
        }
        s+="\nOkres rozliczeniowy \n";
        for(OkresRozliczeniowy okresRozliczeniowy: pracownikService.getOkresRozliczeniow()){
            s+=okresRozliczeniowy.getIdOkresu() + " " + okresRozliczeniowy.getPoczatek() + " " + okresRozliczeniowy.getKoniec() +"\n";
        }
        s+="\nKategorie osiagniec: \n";
        for(KategoriaOsiagniec kategoriaOsiagniec: pracownikService.getKategorieOsiagniec()){
            s+=kategoriaOsiagniec.getIdKategoriaOsiagniec() + " " + kategoriaOsiagniec.getNazwaKategorii() +"\n";
        }
        s+="\nPodkategorie: \n";
        for(PodKategoria podKategoria: pracownikService.getPodKategorie()){
            s+=podKategoria.getIdPodKategorii() +" "+ podKategoria.getMinPunktow()+" "+ podKategoria.getMaxPunktow()+" "+podKategoria.getNazwa()+" " + podKategoria.getKategoriaOsiagniec() +"\n";
        }
        s+="\nOsiagniecia: \n";
        for(Osiagniecie osiagniecie: pracownikService.getOsiagniecia()){
            s+=osiagniecie.getIdOsiagniecia()+" "+osiagniecie.getNazwa()+" "+osiagniecie.getIloscPunktow()+" "+osiagniecie.getData()+" "+osiagniecie.getCzyZatwierdzone()+" "+osiagniecie.getWniosek()+"\n";
        }
        s+="\nWnioski: \n";
        for(Wniosek wniosek: pracownikService.getWnioski()){
            s+=wniosek.getIdWniosku()+ " " +wniosek.getOkresRozliczeniowy()+ " " +wniosek.getPracownik()+"\n";
        }
        s+="\nOceny: \n";
        for(Ocena ocena: pracownikService.getOceny()){
            s+=ocena.getIdOceny()+ " " +ocena.getNazwa()+ " " +ocena.getIloscPunktow()+ " " +ocena.getData()+ " " +ocena.getWniosek()+"\n";
        }
        return s;
    }
    public List<Pracownik> getPracownicy() {
        return pracownikService.getPracownicy();
    }
}
