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
        return pracownikRepo.findByPrzelozonyIdPracownika(id);
    }
    public Pracownik getPrzelozonego(long id) {
        return pracownikRepo.getReferenceById(id).getPrzelozony();
    }
    public Pracownik getPracownik(long id) {
        return pracownikRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Pracownik o podanym id nie zostal znaleziony: " + id));
    }
    public List<Pracownik> getPracownikStanowisko(long id) {
        return pracownikRepo.findByPracownikStanowisko_IdStanowiska(id);
    }
    public List<Pracownik> getPracownikStopienNaukowy(long id) {
        return pracownikRepo.findByStopienNaukowy_IdStopniaNaukowego(id);
    }

//    public List<Pracownik> getPracownikOceny(long idPracownika){
//        return pracownikRepo.findByOcenaSet_IdPracownika(idPracownika);
//    }

    public List<Pracownik> getPracownikOsiagniecia(long idPracownika){
        return pracownikRepo.findByOsiagniecieSet_IdOsiagniecia(idPracownika);
    }

//    public List<Osiagniecie> getOsiagniecieByIdOceny(long idOceny){
//        pracownikRepo.findByOcena_IdOceny()
//    }

//    public List<Pracownik> getPracownikRodzajDzialanosci(long id) {
//        return pracownikRepo.findByRodzajDzialalnosci_IdRodzajDzialalnosci(id);
//    }
    public void addPracownik(Pracownik pracownik) {
        pracownikRepo.save(pracownik);
    }
    public void editPracownik(long id, PracownikDTO pracownikDTO) {
        Pracownik pracownik = getPracownik(id);
        pracownik.setImie(pracownikDTO.getImie());
        pracownik.setNazwisko(pracownikDTO.getNazwisko());
        pracownik.setEmailSluzbowy(pracownikDTO.getEmailSluzbowy());
        pracownik.setStopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowyNazwa()));
        pracownik.setPracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowiskoNazwa()));
        pracownik.setGrupa(grupaService.getGrupa(pracownikDTO.getGrupa()));
        pracownikRepo.save(pracownik);
    }
    public void deletePracownik(Pracownik pracownik) {
        getPracownicyPrzelozonego(pracownik.getIdPracownika()).forEach(p->
                pracownikRepo.getReferenceById(p.getIdPracownika()).setPrzelozony(null));
        pracownikRepo.delete(pracownik);
    }
    public boolean CanUserAccessPracownikData(long idUsera, long idPracownika) {
        return idUsera == idPracownika ||
                getPracownik(idPracownika).getPrzelozony().getIdPracownika() == idUsera;
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
                .idPracownika(p.getIdPracownika())
                .imie(p.getImie())
                .nazwisko(p.getNazwisko())
                .emailSluzbowy(p.getEmailSluzbowy())
                .grupa(p.getGrupa().getNazwa())  //ZMIANA
                .stanowiskoNazwa(p.getPracownikStanowisko().getNazwa())
                .stopienNaukowyNazwa(p.getStopienNaukowy().getNazwa())
                .build();
    }
    public Pracownik buildPracownik(PracownikDTO pracownikDTO) {
        return Pracownik.builder()
                .imie(pracownikDTO.getImie())
                .nazwisko(pracownikDTO.getNazwisko())
                .emailSluzbowy(pracownikDTO.getEmailSluzbowy())
                .stopienNaukowy(stopienNaukowyService.getStopienNaukowy(pracownikDTO.getStopienNaukowyNazwa()))
                .pracownikStanowisko(pracownikStanowiskoService.getPracownikStanowisko(pracownikDTO.getStanowiskoNazwa()))
                .grupa(grupaService.getGrupa(pracownikDTO.getGrupa())) //ZMIANA
                .build();
    }
}
