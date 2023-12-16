package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.service.OsiagniecieService;
import com.example.projektgruptest.service.PodKategorieService;
import com.example.projektgruptest.service.WniosekService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OsiagniecieController {
    private final OsiagniecieService osiagniecieService;
    private final WniosekService wniosekService; //Potrzebny tylko do testów
    private final PodKategorieService podKategorieService; //Potrzebny tylko do testów
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Osiagniecie/test")
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
    @GetMapping("/Osiagniecia")
    public List<OsiagniecieDTO> getOsiagniecia(@AuthenticationPrincipal UserWithPracownik user) {

        List<OsiagniecieDTO> list = new ArrayList<>();
        for(Osiagniecie o : osiagniecieService.getOsiagnieciaPracownika(user.getPracownik().getIdPracownika())) {
            list.add(OsiagniecieDTO.builder()
                            .idOsiagniecia(o.getIdOsiagniecia())
                            .nazwa(o.getNazwa())
                            .iloscPunktow(o.getIloscPunktow())
                            .data(o.getData())
                            .czyZatwierdzone(o.getCzyZatwierdzone())
                            .idWniosku(o.getWniosek().getIdWniosku())
                            .podKategoriaNazwa(o.getPodKategoria().getNazwa())
                    .build());
        }
        return list;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/OsiagnieciaPoId/{id}")
    public List<OsiagniecieDTO> getOsiagnieciaPoId(@PathVariable Long id) {


        List<OsiagniecieDTO> list = new ArrayList<>();
        for(Osiagniecie o : osiagniecieService.getOsiagnieciaPracownika(id)) {
            list.add(OsiagniecieDTO.builder()
                    .idOsiagniecia(o.getIdOsiagniecia())
                    .nazwa(o.getNazwa())
                    .iloscPunktow(o.getIloscPunktow())
                    .data(o.getData())
                    .czyZatwierdzone(o.getCzyZatwierdzone())
                    .idWniosku(o.getWniosek().getIdWniosku())
                    .podKategoriaNazwa(o.getPodKategoria().getNazwa())
                    .build());
        }
        return list;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/Osiagniecie")
    public void dodajOsiagniecie(@RequestBody OsiagniecieDTO osiagniecieDTO) {
        Osiagniecie osiagniecie = Osiagniecie.builder()
                .czyZatwierdzone(false)
                .podKategoria(podKategorieService.getPodkategoria(osiagniecieDTO.getPodKategoriaNazwa()))
                .wniosek(wniosekService.getWniosek(osiagniecieDTO.getIdWniosku()))
                .data(osiagniecieDTO.getData())
                .nazwa(osiagniecieDTO.getNazwa())
                .iloscPunktow(osiagniecieDTO.getIloscPunktow())
                .build();
        osiagniecieService.addOsiagniecie(osiagniecie);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/Osiagniecie/{id}")
    public void edytujOsiagniecie(@PathVariable Long id, @RequestBody OsiagniecieDTO o, @AuthenticationPrincipal UserWithPracownik user) {
        Osiagniecie osiagniecie = osiagniecieService.getOsiagniecie(id);
        for(Osiagniecie os : osiagniecieService.getOsiagnieciaPracownika(user.getPracownik().getIdPracownika())) {
            if(os == osiagniecie) {
                osiagniecie.setNazwa(o.getNazwa());
                osiagniecie.setData(o.getData());
                osiagniecie.setIloscPunktow(o.getIloscPunktow());
                osiagniecie.setPodKategoria(podKategorieService.getPodkategoria(o.getPodKategoriaNazwa()));
                osiagniecieService.addOsiagniecie(osiagniecie);
                break;
            }
        }
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PutMapping("/OsiagniecieZatwierdz/{id}")
    public void edytujOsiagniecie(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        Osiagniecie osiagniecie = osiagniecieService.getOsiagniecie(id);
        for(Osiagniecie os : osiagniecieService.getOsiagnieciaPracownika(user.getPracownik().getIdPracownika())) {
            if(os == osiagniecie) {
                osiagniecie.setCzyZatwierdzone(true);
                osiagniecieService.addOsiagniecie(osiagniecie);
                break;
            }
        }
    }


    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/Osiagniecie/{id}")
    public void usunPracownika(@PathVariable Long id, @AuthenticationPrincipal UserWithPracownik user) {
        Osiagniecie osiagniecie = osiagniecieService.getOsiagniecie(id);
        for(Osiagniecie o : osiagniecieService.getOsiagnieciaPracownika(user.getPracownik().getIdPracownika())) {
            if(o == osiagniecie) {
                osiagniecieService.deleteOsiagniecie(osiagniecie);
                break;
            }
        }
    }
}
