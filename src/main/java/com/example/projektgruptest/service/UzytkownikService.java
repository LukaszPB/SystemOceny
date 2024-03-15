package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.auth.Rola;
import com.example.projektgruptest.model.auth.Uzytkownik;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.modelDTO.UzytkownikDTO;
import com.example.projektgruptest.repo.RolaRepo;
import com.example.projektgruptest.repo.UzytkownikRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UzytkownikService {
    private final UzytkownikRepo uzytkownikRepo;
    private PracownikService pracownikService;


    private final RolaRepo rolaRepo;

    public Uzytkownik getUzytkownik(long id) {return uzytkownikRepo.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Uzytkownik o podanym id nie istnieje: " + id));}
    public List<Uzytkownik> getUzytkownicy(){return uzytkownikRepo.findAll();}

    public void addUzytkownik(UzytkownikDTO uzytkownikDTO)
    {
        Uzytkownik uzytkownik = buildUzytkownik(uzytkownikDTO);
        uzytkownikRepo.save(uzytkownik);
    }
    private Uzytkownik buildUzytkownik(UzytkownikDTO uzytkownikDTO){
        Rola rola = rolaRepo.findByNazwa(uzytkownikDTO.getRola());
        return Uzytkownik.builder()
                .login(uzytkownikDTO.getLogin())
                .haslo(uzytkownikDTO.getHaslo())
                .rola(rolaRepo.findByNazwa(uzytkownikDTO.getRola()))
                .pracownik(null)
                .build();
    }
    public void deleteUzytkownik(Uzytkownik uzytkownik){
        if(uzytkownik.getPracownik()!=null){
            Pracownik pracownik = uzytkownik.getPracownik();
            uzytkownik.setPracownik(null);
            pracownikService.deletePracownik(pracownik);
        }
        uzytkownikRepo.delete(uzytkownik);
    }


    public List<UzytkownikDTO> convertListToDTO(List<Uzytkownik> uzytkownikList) {
        return uzytkownikList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public UzytkownikDTO convertToDTO(Uzytkownik uzytkownik) {

        Long idPracownika = uzytkownik.getPracownik() == null ? null : uzytkownik.getPracownik().getId();
        return UzytkownikDTO.builder()
                .id(uzytkownik.getId())
                .login(uzytkownik.getLogin())
                .haslo(uzytkownik.getHaslo())
                .rola(uzytkownik.getRola().getNazwa())
                .idPracownika(idPracownika)
                .build();

    }

    public void editUzytkownik(UzytkownikDTO uzytkownikDTO, Long id) {
        Uzytkownik uzytkownik = getUzytkownik(id);
        Rola rola = rolaRepo.findByNazwa(uzytkownikDTO.getRola());
        rola = rola != null ? rola : uzytkownik.getRola();
        uzytkownik.setLogin(uzytkownikDTO.getLogin());
        uzytkownik.setHaslo(uzytkownikDTO.getHaslo());
        uzytkownik.setRola(rola);
        if(uzytkownik.getPracownik()==null && rola.getNazwa().equals("PRACOWNIK"))
        {
            //TRZEBA BY DODAC METODE CO SPRAWDZA CZY JAKIS UZYTKOWNIK NIE MA JUZ PRZYPISANEGO TEGO PRACOWNIKA
            uzytkownik.setPracownik(pracownikService.getPracownik(uzytkownikDTO.getIdPracownika()));
        }
        uzytkownikRepo.save(uzytkownik);
    }





//    public Uzytkownik addUzytkownik(UzytkownikDTO uzytkownikDTO) //listowanie uzytkownikow
//    {
//        Uzytkownik uzytkownik = null;
//        if(uzytkownikDTO.getRola().equals("ADMIN") || uzytkownikDTO.getRola().equals("KOMISJA") )
//        {
//             uzytkownik = Uzytkownik.builder()
//                    .login(uzytkownikDTO.getLogin())
//                    .haslo(uzytkownikDTO.getHaslo())
//                    .rola(rolaRepo.findByNazwa(uzytkownikDTO.getRola()))
//                    .pracownik(null)
//                    .build();
//        }
//        else
//        {
//            Pracownik pracownik = Pracownik.builder()
//                    .imie(uzytkownikDTO.getImiePracownika())
//                    .nazwisko(uzytkownikDTO.getNazwiskoPracownika())
//                    .build();
//
//            pracownikService.addPracownik(pracownik);
//            uzytkownik = Uzytkownik.builder()
//                    .login(uzytkownikDTO.getLogin())
//                    .haslo(uzytkownikDTO.getHaslo())
//                    .rola(rolaRepo.findByNazwa(uzytkownikDTO.getRola()))
//                    .pracownik(pracownik)
//                    .build();
//        }
//
//        return uzytkownik;
//    }
}
