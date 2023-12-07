package com.example.projektgruptest.service;

import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.repo.OcenaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OcenaService {
    private final OcenaRepo ocenaRepo;
    public Ocena getOcena(long id) {
        return ocenaRepo.getReferenceById(id);
    }
    public List<Ocena> getOceny() {
        return ocenaRepo.findAll();
    }
    public List<Ocena> getOcenyPracownika(long id) {
        return ocenaRepo.findByWniosekPracownikIdPracownika(id);
    }
    public void addOcena(Ocena ocena) {
        ocenaRepo.save(ocena);
    }
    public void deleteOcena(Ocena ocena) {
        ocenaRepo.delete(ocena);
    }
}
