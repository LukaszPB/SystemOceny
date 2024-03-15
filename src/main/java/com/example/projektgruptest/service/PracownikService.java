package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.modelDTO.PracownikDTO;
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
    private final GrupaService grupaService;
    private final PracownikStanowiskoService pracownikStanowiskoService;

    public List<Pracownik> getPracownicy() {
        return pracownikRepo.findAll();
    }
    public List<Pracownik> getPracownicyPrzelozonego(long id) {
        return pracownikRepo.findByPrzelozonyId(id);
    }
    public Pracownik getPrzelozonego(long id) {
        return pracownikRepo.getReferenceById(id).getPrzelozony();
    }
    public Pracownik getPracownik(long id) {
        return pracownikRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Pracownik o podanym id nie zostal znaleziony: " + id));
    }

    public List<Pracownik> getPracownikWydzial(String nazwaWydzialu){
        return pracownikRepo.findByWydzialKatedra_NazwaWydzialu(nazwaWydzialu);
    }
    public List<Pracownik> getPracownikKatedra(String nazwaKatedry){
        return pracownikRepo.findByWydzialKatedra_NazwaKatedry(nazwaKatedry);
    }

    public List<Pracownik> getPracownikStanowisko(long id) {
        return pracownikRepo.findByPracownikStanowisko_Id(id);
    }
    public List<Pracownik> getPracownikStopienNaukowy(long id) {
        return pracownikRepo.findByStopienNaukowy_Id(id);
    }

    public List<Pracownik> getPracownikOsiagniecia(long idPracownika){
        return pracownikRepo.findByOsiagniecieSet_Id(idPracownika);
    }

    public void addPracownik(Pracownik pracownik) {
        pracownikRepo.save(pracownik);
    }
    public void editPracownik(long id, PracownikDTO pracownikDTO) {
        Pracownik pracownik = getPracownik(id);
        pracownik.setImie(pracownikDTO.getImie());
        pracownik.setNazwisko(pracownikDTO.getNazwisko());
        pracownik.setEmail(pracownikDTO.getEmail());
        pracownik.setStopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowy()));
        pracownik.setPracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowisko()));
        pracownik.setGrupa(grupaService.getGrupa(pracownikDTO.getGrupa()));
        pracownikRepo.save(pracownik);
    }
    public void deletePracownik(Pracownik pracownik) {
        getPracownicyPrzelozonego(pracownik.getId()).forEach(p->
                pracownikRepo.getReferenceById(p.getId()).setPrzelozony(null));
        pracownikRepo.delete(pracownik);
    }
    public boolean CanUserAccessPracownikData(long idUsera, long idPracownika) {
        return idUsera == idPracownika ||
                getPracownik(idPracownika).getPrzelozony().getId() == idUsera;
    }
    public List<PracownikDTO> convertListToDTO(List<Pracownik> pracownikList) {
        return pracownikList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public PracownikDTO convertToDTO(Pracownik p) {

        if(p==null)
            return null;
        return PracownikDTO.builder()
                .id(p.getId())
                .imie(p.getImie())
                .nazwisko(p.getNazwisko())
                .email(p.getEmail())
                .grupa(p.getGrupa().getNazwa())  //ZMIANA
                .stanowisko(p.getPracownikStanowisko().getNazwa())
                .stopienNaukowy(p.getStopienNaukowy().getNazwa())
                .build();
    }
    public Pracownik buildPracownik(PracownikDTO pracownikDTO) {
        return Pracownik.builder()
                .imie(pracownikDTO.getImie())
                .nazwisko(pracownikDTO.getNazwisko())
                .email(pracownikDTO.getEmail())
                .stopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowy()))
                .pracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowisko()))
                .grupa(grupaService.getGrupa(pracownikDTO.getGrupa())) //ZMIANA
                .build();
    }
}
