package com.example.projektgruptest.service;

import com.example.projektgruptest.model.KryteriaOceny;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.modelDTO.KryteriaOcenyDTO;
import com.example.projektgruptest.repo.KryteriaOcenyRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class KryteriaOcenyService {
    private final KryteriaOcenyRepo kryteriaOcenyRepo;
    private final StopienNaukowyService stopienNaukowyService;
    public List<KryteriaOceny> getKryteriaOceny() {
        return kryteriaOcenyRepo.findAll();
    }
    public KryteriaOceny getKryteriumOceny(Pracownik pracownik) {
        boolean czyMaTytulNaukowy = czyMaTytulNaukowy(pracownik.getStopienNaukowy().getNazwa());
        boolean czKierownik = czyKierownik(pracownik.getPracownikStanowisko().getNazwa());
        for(KryteriaOceny kryteriaOceny : getKryteriaOceny()) {
            if(kryteriaOceny.getCzyNaStanowiskuKierowniczym() == czKierownik &&
                kryteriaOceny.getCzyPosiadaStopienNaukowy() == czyMaTytulNaukowy) {
                return  kryteriaOceny;
            }
        }
        return null;
    }
    public boolean czyMaTytulNaukowy(String nazwa) {
        return (Objects.equals(nazwa, "DOKTOR") || Objects.equals(nazwa, "DOKTOR HABILITOWANY") ||
                Objects.equals(nazwa, "PROFESOR"));
    }
    public boolean czyKierownik(String nazwa) {
        return (Objects.equals(nazwa, "DZIEKAN") || Objects.equals(nazwa, "PRODZIEKAN"));
    }
    public List<KryteriaOcenyDTO> convertListToDTO(List<KryteriaOceny> kryteriaOcenyList) {
        return kryteriaOcenyList
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public KryteriaOcenyDTO convertToDTO(KryteriaOceny kryteriaOceny) {
        return KryteriaOcenyDTO.builder()
                .progPozytywnejOcenyDO(kryteriaOceny.getProgPozytywnejOcenyDO())
                .progPozytywnejOcenyNB(kryteriaOceny.getProgPozytywnejOcenyNB())
                .IdKryterium(kryteriaOceny.getIdKryterium())
                .progOcenyZWyroznieniemDO(kryteriaOceny.getProgOcenyZWyroznieniemDO())
                .progOcenyZWyroznieniemNB(kryteriaOceny.getProgOcenyZWyroznieniemNB())
                .czyPosiadaStopienNaukowy(kryteriaOceny.getCzyPosiadaStopienNaukowy())
                .czyNaStanowiskuKierowniczym(kryteriaOceny.getCzyNaStanowiskuKierowniczym())
                .build();
    }
}
