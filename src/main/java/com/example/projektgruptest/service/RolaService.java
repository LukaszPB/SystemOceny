package com.example.projektgruptest.service;

import com.example.projektgruptest.model.auth.Rola;
import com.example.projektgruptest.model.auth.Uzytkownik;
import com.example.projektgruptest.repo.RolaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RolaService {
    private UzytkownikService uzytkownikService;
    private final RolaRepo rolaRepo;
    public List<Rola> getRole(){return rolaRepo.findAll();}
    public Rola getRola(long id){return rolaRepo.getReferenceById(id);}
    public Rola getRola(String nazwa){return rolaRepo.findByNazwa(nazwa);}
    public Rola getRolaUzytkownika(long id){
       Uzytkownik uzytkownik = uzytkownikService.getUzytkownik(id);
       return uzytkownik.getRola();
    }

}
