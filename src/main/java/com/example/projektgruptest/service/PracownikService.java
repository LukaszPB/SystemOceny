package com.example.projektgruptest.service;

import com.example.projektgruptest.model.*;
import com.example.projektgruptest.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PracownikService {
    private final StopienNaukowyRepo stopienNaukowyRepo;
    private final RodzajDzialalnosciRepo rodzajDzialalnosciRepo;
    private final PracownikStanowiskoRepo pracownikStanowiskoRepo;
    private final OkresRozliczeniowyRepo okresRozliczeniowyRepo;
    private final KategoriaOsiagniecRepo kategoriaOsiagniecRepo;
    private final PodKategoriaRepo podKategoriaRepo;
    private final OsiagniecieRepo osiagniecieRepo;
    private final WniosekRepo wniosekRepo;
    private final OcenaRepo ocenaRepo;
    private final PracownikRepo pracownikRepo;
    public List<StopienNaukowy> getStopnieNaukowe() {
        return stopienNaukowyRepo.findAll();
    }
    public List<RodzajDzialalnosci> getRodzajeDzialalnosci() {
        return rodzajDzialalnosciRepo.findAll();
    }
    public List<PracownikStanowisko> getPracownikStanowisko() {
        return pracownikStanowiskoRepo.findAll();
    }
    public List<Pracownik> getPracownik() {
        return pracownikRepo.findAll();
    }
    public List<OkresRozliczeniowy> getOkresRozliczeniow (){
        return okresRozliczeniowyRepo.findAll();
    }
    public List<KategoriaOsiagniec> getKategorieOsiagniec(){
        return kategoriaOsiagniecRepo.findAll();
    }
    public List<PodKategoria> getPodKategorie(){
        return podKategoriaRepo.findAll();
    }
    public List<Osiagniecie> getOsiagniecia(){
        return osiagniecieRepo.findAll();
    }
    public List<Wniosek> getWnioski(){
        return wniosekRepo.findAll();
    }
    public List<Ocena> getOceny(){
        return ocenaRepo.findAll();
    }
    public List<Pracownik> getPracownicy() {
        return pracownikRepo.findAll();
    }
    public Pracownik getPracownik(long id) {
        return pracownikRepo.getReferenceById(id);
    }
    public void addPracownik(Pracownik pracownik) {
        pracownikRepo.save(pracownik);
    }
    public void deletePracownik(Pracownik pracownik) {
        //osiagniecieRepo.deleteOsiagniecieByPracownikId(pracownik.getIdPracownika());
        //ocenaRepo.deleteOcenaByPracownikId(pracownik.getIdPracownika());
        //wniosekRepo.deleteWniosekByPracownikId(pracownik.getIdPracownika());
        pracownikRepo.delete(pracownik);
    }
}
