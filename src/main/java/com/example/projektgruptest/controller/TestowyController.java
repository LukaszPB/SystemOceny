package com.example.projektgruptest.controller;

import com.example.projektgruptest.model.*;
import com.example.projektgruptest.service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestowyController {

    private final TestowyService testowyService;
    private final KategoriaService kategoriaService;
    private final PodKategorieService podKategorieService;
    private final RodzajDzialanosciService rodzajDzialanosciService;
    private final OkresRozliczeniowyService okresRozliczeniowyService;
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/testowy")
    public String getStopnieNaukowe() {
        testowyService.addPracownik(Pracownik.builder()
                .pracownikStanowisko(testowyService.getPracownikStanowisko().get(0))
                .rodzajDzialalnosci(testowyService.getRodzajeDzialalnosci().get(0))
                .stopienNaukowy(testowyService.getStopnieNaukowe().get(0))
                .imie("ADAM")
                .nazwisko("NOWAK")
                .emailSluzbowy("adamNowak@pb.edu.pl")
                .build());
        //pracownikService.deletePracownik(pracownikService.getPracownik(1));

        String s="Stopnie naukowe:\n";
        for(StopienNaukowy element : testowyService.getStopnieNaukowe()) {
            s += element.getIdStopniaNaukowego() + " " + element.getNazwa() + "\n";
        }
        s+="\nRodzaje działalności:\n";
        for(RodzajDzialalnosci element : testowyService.getRodzajeDzialalnosci()) {
            s += element.getIdRodzajDzialalnosci() + " " + element.getNazwa() + "\n";
        }
        s+="\nStanowiska:\n";
        for(PracownikStanowisko element : testowyService.getPracownikStanowisko()) {
            s += element.getIdStanowiska() + " " + element.getNazwa() + "\n";
        }
        s+="\nPracownicy:\n";
        for(Pracownik element : testowyService.getPracownik()) {
            s += element.getIdPracownika() + " " + element.getPracownikStanowisko().getNazwa()
                    + " "  + element.getRodzajDzialalnosci().getNazwa() + " "  +
                    element.getStopienNaukowy().getNazwa() + " "  +
                    element.getImie() + " "  + element.getNazwisko() + " "  +
                    element.getEmailSluzbowy() + " "  + "\n";
        }

        kategoriaService.addKategoria(KategoriaOsiagniec.builder()
                .nazwaKategorii("Nowa Kategoria")
                .rodzajDzialalnosci(rodzajDzialanosciService.getRodzajeDzialanosci().get(0)).build());

        s+="\nPO DODANIU KATEGORII:\n";
        for(KategoriaOsiagniec element : kategoriaService.getKategorie()) {
            s += element;
        }
        s+="\nWszystkie pod Kategorie:\n";
        for(PodKategoria element : podKategorieService.getPodKategorie()) {
            s += element;
        }
        kategoriaService.deleteKategoria(kategoriaService.getKategoria(1));
        s+="\nPO USUNIECIU KATEGORII:\n";
        for(KategoriaOsiagniec element : kategoriaService.getKategorie()) {
            s += element;
        }
        s+="\nTest czy usuwa pod kategorie razem z kategoriami:\n";
        for(PodKategoria element : podKategorieService.getPodKategorie()) {
            s += element;
        }
//        osiagniecieService.addOsiagniecie(Osiagniecie.builder()
//                .czyZatwierdzone(false)
//                .data(new Date())
//                .nazwa("Publikacja naukowa")
//                .iloscPunktow(20)
//                .wniosek(wniosekService.getWniosek(1))
//                .podKategoria(podKategorieService.getPodKategorie().get(0))
//                .build());

        s+="\nOkres rozliczeniowy \n";
        for(OkresRozliczeniowy okresRozliczeniowy: testowyService.getOkresRozliczeniow()){
            s+=okresRozliczeniowy.getIdOkresu() + " " + okresRozliczeniowy.getPoczatek() + " " + okresRozliczeniowy.getKoniec() +"\n";
        }
        s+="\nKategorie osiagniec: \n";
        for(KategoriaOsiagniec kategoriaOsiagniec: testowyService.getKategorieOsiagniec()){
            s+=kategoriaOsiagniec.getIdKategoriaOsiagniec() + " " + kategoriaOsiagniec.getNazwaKategorii() +"\n";
        }
        s+="\nPodkategorie: \n";
        for(PodKategoria podKategoria: testowyService.getPodKategorie()){
            s+=podKategoria.getIdPodKategorii() +" "+ podKategoria.getMinPunktow()+" "+ podKategoria.getMaxPunktow()+" "+podKategoria.getNazwa()+" " + podKategoria.getKategoriaOsiagniec() +"\n";
        }




        s+="\nOsiagniecia: \n";
        for(Osiagniecie osiagniecie: testowyService.getOsiagniecia()){
            s+=osiagniecie.getIdOsiagniecia()+" "+osiagniecie.getNazwa()+" "+osiagniecie.getIloscPunktow()+" "+osiagniecie.getData()+" "+osiagniecie.getCzyZatwierdzone()+" "+osiagniecie.getWniosek()+"\n";
        }
        s+="\nWnioski: \n";
        for(Wniosek wniosek: testowyService.getWnioski()){
            s+=wniosek.getIdWniosku()+ " " +wniosek.getOkresRozliczeniowy()+ " " +wniosek.getPracownik()+"\n";
        }
        s+="\nOceny: \n";
        for(Ocena ocena: testowyService.getOceny()){
            s+=ocena.getIdOceny()+ " " +ocena.getNazwa()+ " " +ocena.getIloscPunktow()+ " " +ocena.getData()+ " " +ocena.getWniosek()+"\n";
        }
        return s;
    }
    public List<Pracownik> getPracownicy() {
        return testowyService.getPracownicy();
    }
}
