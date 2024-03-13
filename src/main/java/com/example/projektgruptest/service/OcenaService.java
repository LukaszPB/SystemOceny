package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.repo.OcenaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OcenaService {
    private final OcenaRepo ocenaRepo;
    public Ocena getOcena(long id) {
        return ocenaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Ocena o podanym id nie istnieje: " + id));
    }
    public List<Ocena> getOceny() {
        return ocenaRepo.findAll();
    }
    public List<Ocena> getOcenyPracownika(long idPracownika) {
        Pracownik pracownik = ocenaRepo.findByPracownik_Id(idPracownika);
        return new ArrayList<>(pracownik.getOcenaSet()); //TODO: POPPRAW
    }
    public void addOcena(Ocena ocena) {
        ocenaRepo.save(ocena);
    }
    public void deleteOcena(Ocena ocena) {
        ocenaRepo.delete(ocena);
    }
    public boolean canUserAccessThisOcena(long idOceny,long idPracownika){
        Ocena ocena  = getOcena(idOceny);
        return ocena.getPracownik().getId() == idPracownika;
    }





}
