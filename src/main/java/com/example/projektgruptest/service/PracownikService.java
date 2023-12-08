package com.example.projektgruptest.service;

import com.example.projektgruptest.model.*;
import com.example.projektgruptest.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PracownikService {

    private final PracownikStanowiskoRepo pracownikStanowiskoRepo;

    private final PracownikRepo pracownikRepo;



    public List<Pracownik> getPracownicy() {
        return pracownikRepo.findAll();
    }
    public List<Pracownik> getPracownicyPrzelozonego(long id) {
        return pracownikRepo.findByPrzelozonyIdPracownika(id);
    }
    public Pracownik getPrzelozonego(long id) {
        return pracownikRepo.getReferenceById(id).getPrzelozony();
    }
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
    public void deletePracownik(Pracownik pracownik) {
        getPracownicyPrzelozonego(pracownik.getIdPracownika()).forEach(p->p.setPrzelozony(null));
        pracownikRepo.delete(pracownik);
    }
}
