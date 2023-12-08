package com.example.projektgruptest.service;

import com.example.projektgruptest.model.auth.Uzytkownik;
import com.example.projektgruptest.repo.UzytkownikRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UzytkownikService {
    private final UzytkownikRepo uzytkownikRepo;
    public Uzytkownik getUzytkownik(long id) {return uzytkownikRepo.getReferenceById(id);}
    public List<Uzytkownik> getUzytkownicy(){return uzytkownikRepo.findAll();}
    public void addUzytkownik(Uzytkownik uzytkownik){uzytkownikRepo.save(uzytkownik);}
    public void deleteUzytkownik(Uzytkownik uzytkownik){uzytkownikRepo.delete(uzytkownik);}
}
