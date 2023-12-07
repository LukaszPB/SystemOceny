package com.example.projektgruptest.service;

import com.example.projektgruptest.model.PracownikStanowisko;
import com.example.projektgruptest.repo.PracownikStanowiskoRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PracownikStanowiskoService {
    private final PracownikStanowiskoRepo pracownikStanowiskoRepo;
    public PracownikStanowisko getPracownikStanowisko(long id){return pracownikStanowiskoRepo.getReferenceById(id);}
    public List<PracownikStanowisko> getPracownicyStanowiska(){return pracownikStanowiskoRepo.findAll();}
    public void addPracownikStanowisko(PracownikStanowisko stanowisko){pracownikStanowiskoRepo.save(stanowisko);}
    public void deletePracownikStanowisko(PracownikStanowisko stanowisko){pracownikStanowiskoRepo.delete(stanowisko);}
}
