package com.example.projektgruptest.service;

import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.auth.Uzytkownik;
import com.example.projektgruptest.modelDTO.UzytkownikDTO;
import com.example.projektgruptest.repo.RolaRepo;
import com.example.projektgruptest.repo.UzytkownikRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UzytkownikService {
    private final UzytkownikRepo uzytkownikRepo;
    private PracownikService pracownikService;
    private final RolaRepo rolaRepo;
    public Uzytkownik getUzytkownik(long id) {return uzytkownikRepo.getReferenceById(id);}
    public List<Uzytkownik> getUzytkownicy(){return uzytkownikRepo.findAll();}
    public void addUzytkownik(Uzytkownik uzytkownik){uzytkownikRepo.save(uzytkownik);}
    public void deleteUzytkownik(Uzytkownik uzytkownik){
        if(uzytkownik.getPracownik()!=null){
            Pracownik pracownik = uzytkownik.getPracownik();
            uzytkownik.setPracownik(null);
            pracownikService.deletePracownik(pracownik);
        }
        uzytkownikRepo.delete(uzytkownik);
    }
    public UzytkownikDTO addUzytkownikDTO(Uzytkownik uzytkownik)
    {
       UzytkownikDTO uzytkownikDTO = UzytkownikDTO.builder()
                .id(uzytkownik.getId())
                .login(uzytkownik.getLogin())
                .haslo(uzytkownik.getHaslo())
                .nazwaRoli(uzytkownik.getRola().getNazwa())
                .idPracownika(uzytkownik.getPracownik().getIdPracownika())
                .imiePracownika(uzytkownik.getPracownik().getImie())
                .nazwiskoPracownika(uzytkownik.getPracownik().getNazwisko())
                .build();
        return uzytkownikDTO;
    }
    public UzytkownikDTO addUzytkownikDTOBezPracownika(Uzytkownik uzytkownik)
    {
        UzytkownikDTO uzytkownikDTO = UzytkownikDTO.builder()
                .id(uzytkownik.getId())
                .login(uzytkownik.getLogin())
                .haslo(uzytkownik.getHaslo())
                .nazwaRoli(uzytkownik.getRola().getNazwa())
                .build();
        return uzytkownikDTO;
    }
    public Uzytkownik addUzytkownik(UzytkownikDTO uzytkownikDTO)
    {
        Uzytkownik uzytkownik = null;
        if(uzytkownikDTO.getNazwaRoli().equals("ADMIN") || uzytkownikDTO.getNazwaRoli().equals("KOMISJA") )
        {
             uzytkownik = Uzytkownik.builder()
                    .login(uzytkownikDTO.getLogin())
                    .haslo(uzytkownikDTO.getHaslo())
                    .rola(rolaRepo.findByNazwa(uzytkownikDTO.getNazwaRoli()))
                    .pracownik(null)
                    .build();
        }
        else
        {
            Pracownik pracownik = new Pracownik();
            pracownikService.addPracownik(pracownik);
            uzytkownik = Uzytkownik.builder()
                    .login(uzytkownikDTO.getLogin())
                    .haslo(uzytkownikDTO.getHaslo())
                    .rola(rolaRepo.findByNazwa(uzytkownikDTO.getNazwaRoli()))
                    .pracownik(pracownik)
                    .build();
        }

        return uzytkownik;
    }
}
