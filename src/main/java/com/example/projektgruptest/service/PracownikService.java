package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.modelDTO.PracownikDTO;
import com.example.projektgruptest.repo.PracownikRepo;
import com.example.projektgruptest.repo.WydzialKatedraRepo;
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
    private final WydzialKatedraRepo wydzialKatedraRepo;

    public List<Pracownik> getPracownicy() {
        return pracownikRepo.findAll();
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

    public void addPracownik(PracownikDTO pracownikDTO) {
        if(editPracownik(pracownikDTO)) {
            return;
        }
        Pracownik pracownik = buildPracownik(pracownikDTO);
        pracownikRepo.save(pracownik);
    }
    public boolean editPracownik(PracownikDTO pracownikDTO) {
        Pracownik pracownik;
        try {
            pracownik = getPracownik(pracownikDTO.getId());
        }
        catch (ResourceNotFoundException ex) {
            return false;
        }
        pracownik.setImie(pracownikDTO.getImie());
        pracownik.setNazwisko(pracownikDTO.getNazwisko());
        pracownik.setStopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowy()));
        pracownik.setPracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowisko()));
        pracownik.setWydzialKatedra(wydzialKatedraRepo.findWydzialKatedraByNazwaKatedry(pracownikDTO.getKatedra()));
        pracownik.setCzyMoznaOcenic(pracownikDTO.getCzyMoznaOcenic());
        pracownik.setGrupa(grupaService.getGrupa(pracownikDTO.getGrupa()));
        pracownikRepo.save(pracownik);
        return true;
    }
    public void deletePracownik(Pracownik pracownik) {
        pracownikRepo.delete(pracownik);
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
                .grupa(p.getGrupa().getNazwa())
                .stanowisko(p.getPracownikStanowisko().getNazwa())
                .stopienNaukowy(p.getStopienNaukowy().getNazwa())
                .katedra(p.getWydzialKatedra().getNazwaKatedry())
                .czyMoznaOcenic(p.getCzyMoznaOcenic())
                .build();
    }
    public Pracownik buildPracownik(PracownikDTO pracownikDTO) {
        return Pracownik.builder()
                .id(pracownikDTO.getId())
                .imie(pracownikDTO.getImie())
                .nazwisko(pracownikDTO.getNazwisko())
                .stopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowy()))
                .pracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowisko()))
                .wydzialKatedra(wydzialKatedraRepo.findWydzialKatedraByNazwaKatedry(pracownikDTO.getKatedra()))
                .grupa(grupaService.getGrupa(pracownikDTO.getGrupa()))
                .czyMoznaOcenic(pracownikDTO.getCzyMoznaOcenic())
                .build();
    }
}
