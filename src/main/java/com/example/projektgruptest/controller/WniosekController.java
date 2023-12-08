package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.OkresRozliczeniowy;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.PracownikDTO;
import com.example.projektgruptest.model.Wniosek;
import com.example.projektgruptest.modelDTO.WniosekDTO;
import com.example.projektgruptest.service.OcenaService;
import com.example.projektgruptest.service.OkresRozliczeniowyService;
import com.example.projektgruptest.service.PracownikService;
import com.example.projektgruptest.service.WniosekService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WniosekController {
    private final WniosekService wniosekService;
    private final PracownikService pracownikService; // Potrzebne tylko do testów
    private OcenaService ocenaService;
    private final OkresRozliczeniowyService okresRozliczeniowyService; // Potrzebne tylko do testów
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Wnioski/test")
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
    @SecurityRequirement(name = "JWT Authentication")
    @GetMapping("/Wnioski")
    public List<WniosekDTO> getWnioski(@AuthenticationPrincipal UserWithPracownik user){
        List<WniosekDTO> list = new ArrayList<>();
        for(Wniosek w: wniosekService.getWnioskiPraconika(user.getPracownik().getIdPracownika()))
        {
            Pracownik pracownik = pracownikService.getPracownik(user.getPracownik().getIdPracownika());
            PracownikDTO pracownikDTO = new PracownikDTO(pracownik.getIdPracownika(),pracownik.getImie(),
                    pracownik.getNazwisko(),pracownik.getEmailSluzbowy(),pracownik.getStopienNaukowy().getNazwa(),
                    pracownik.getPracownikStanowisko().getNazwa(),pracownik.getRodzajDzialalnosci().getNazwa());
            list.add(WniosekDTO.builder()
                                    .idWniosku(w.getIdWniosku())
                                    .idPracownika(user.getPracownik().getIdPracownika())
                                    .dataPoczatkowa(w.getOkresRozliczeniowy().getPoczatek().toString())
                                    .dataKoncowa(w.getOkresRozliczeniowy().getKoniec().toString())
                                    .idOceny(w.getOcena().getIdOceny())
                                    .listaIdOsiagniec(w.getOsiagniecieSet().stream().map(osiagniecie -> osiagniecie.getIdOsiagniecia()).collect(Collectors.toList()))
                        .build());
        }
        return list;
    }
    @SecurityRequirement(name = "JWT Authentication")
    @PostMapping("/Wniosek")
    public void dodajWniosek(@RequestBody WniosekDTO wniosekDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        OkresRozliczeniowy okresRozliczeniowy = OkresRozliczeniowy.builder()
                .poczatek(LocalDateTime.parse(wniosekDTO.getDataPoczatkowa(), formatter))
                .koniec(LocalDateTime.parse(wniosekDTO.getDataKoncowa(), formatter))
                .build();
        okresRozliczeniowyService.addOkresRozliczeniowy(okresRozliczeniowy);

        Wniosek wniosek = Wniosek.builder()
                .osiagniecieSet(new HashSet<>())
                .okresRozliczeniowy(okresRozliczeniowy)
                .pracownik(pracownikService.getPracownik(wniosekDTO.getIdPracownika()))
                .ocena(ocenaService.getOcena(wniosekDTO.getIdOceny()))
                .build();
        wniosekService.addWniosek(wniosek);
    }
    @SecurityRequirement(name = "JWT Authentication")
    @DeleteMapping("/Wniosek/{id}/")
    public void usunWniosek(@PathVariable Long id,@AuthenticationPrincipal UserWithPracownik user) {
        Wniosek wniosek = wniosekService.getWniosek(id);
        for(Wniosek wn : wniosekService.getWnioskiPraconika(user.getPracownik().getIdPracownika())) {
            if(wn == wniosek) {
                wniosekService.deleteWniosek(wniosek);
                break;
            }
        }
    }

}
