package com.example.projektgruptest.service;

import com.example.projektgruptest.model.*;
import com.example.projektgruptest.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PracownikService {

    private final PracownikRepo pracownikRepo;
    private final StopienNaukowyService stopienNaukowyService;
    private final RodzajDzialanosciService rodzajDzialanosciService;
    private final PracownikStanowiskoService pracownikStanowiskoService;

    public List<PracownikDTO> getPracownicy() {
        return convertListToDTO(pracownikRepo.findAll());
    }
    public List<PracownikDTO> getPracownicyPrzelozonego(long id) {
        return convertListToDTO(pracownikRepo.findByPrzelozonyIdPracownika(id));
    }
    public PracownikDTO getPrzelozonego(long id) {
        return convertToDTO(pracownikRepo.getReferenceById(id).getPrzelozony());
    }
//    public PracownikDTO getPracownikDTO(long id) {
//        return convertToDTO(pracownikRepo.getReferenceById(id));
//    }
    public Pracownik getPracownik(long id) {
        return pracownikRepo.getReferenceById(id);
    }
    public List<Pracownik> getPracownikStanowisko(long id) {
        return pracownikRepo.findByPracownikStanowisko_IdStanowiska(id);
    }
    public List<Pracownik> getPracownikStopienNaukowy(long id) {
        return pracownikRepo.findByStopienNaukowy_IdStopniaNaukowego(id);
    }
    public List<Pracownik> getPracownikRodzajDzialanosci(long id) {
        return pracownikRepo.findByRodzajDzialalnosci_IdRodzajDzialalnosci(id);
    }
    public void addPracownik(Pracownik pracownik) {
        pracownikRepo.save(pracownik);
    }
    public void editPracownik(long id, PracownikDTO pracownikDTO) {
        Pracownik pracownik = getPracownik(id);
        pracownik.setImie(pracownikDTO.getImie());
        pracownik.setNazwisko(pracownikDTO.getNazwisko());
        pracownik.setEmailSluzbowy(pracownikDTO.getEmailSluzbowy());
        pracownik.setStopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowyNazwa()));
        pracownik.setPracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowiskoNazwa()));
        pracownik.setRodzajDzialalnosci(rodzajDzialanosciService.getRodzajDzialanosci(pracownikDTO.getRodzajDzialalnosciNazwa()));
        pracownikRepo.save(pracownik);
    }
    public void deletePracownik(Pracownik pracownik) {
        getPracownicyPrzelozonego(pracownik.getIdPracownika()).forEach(p->
                pracownikRepo.getReferenceById(p.getIdPracownika()).setPrzelozony(null));
        pracownikRepo.delete(pracownik);
    }
    private List<PracownikDTO> convertListToDTO(List<Pracownik> pracownicy) {
        List<PracownikDTO> list = new ArrayList<>();
        for(Pracownik p : pracownicy) {
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
    private PracownikDTO convertToDTO(Pracownik p) {

        return PracownikDTO.builder()
                .idPracownika(p.getIdPracownika())
                .imie(p.getImie())
                .nazwisko(p.getNazwisko())
                .emailSluzbowy(p.getEmailSluzbowy())
                .rodzajDzialalnosciNazwa(p.getRodzajDzialalnosci().getNazwa())
                .stanowiskoNazwa(p.getPracownikStanowisko().getNazwa())
                .stopienNaukowyNazwa(p.getStopienNaukowy().getNazwa())
                .build();
    }
    public Pracownik buildPracownik(PracownikDTO pracownikDTO) {
        return Pracownik.builder()
                .imie(pracownikDTO.getImie())
                .nazwisko(pracownikDTO.getNazwisko())
                .emailSluzbowy(pracownikDTO.getEmailSluzbowy())
                .stopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowyNazwa()))
                .pracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowiskoNazwa()))
                .rodzajDzialalnosci(rodzajDzialanosciService.getRodzajDzialanosci(pracownikDTO.getRodzajDzialalnosciNazwa()))
                .build();
    }
}
