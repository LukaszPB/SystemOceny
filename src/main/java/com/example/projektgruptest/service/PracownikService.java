package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.PracownikDTO;
import com.example.projektgruptest.model.Wniosek;
import com.example.projektgruptest.repo.PracownikRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PracownikService {

    private final PracownikRepo pracownikRepo;
    private final StopienNaukowyService stopienNaukowyService;
    private final RodzajDzialanosciService rodzajDzialanosciService;
    private final PracownikStanowiskoService pracownikStanowiskoService;

    public List<PracownikDTO> getPracownicy() {
        return pracownikRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public List<PracownikDTO> getPracownicyPrzelozonego(long id) {
        return pracownikRepo.findByPrzelozonyIdPracownika(id).stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }
    public PracownikDTO getPrzelozonego(long id) {
        return convertToDTO(pracownikRepo.getReferenceById(id).getPrzelozony());
    }
    public Pracownik getPracownik(long id) {
        return pracownikRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Pracownik o podanym id nie zostal znaleziony: " + id));
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
    public PracownikDTO convertToDTO(Pracownik p) {

        if(p==null)
            return null;
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
