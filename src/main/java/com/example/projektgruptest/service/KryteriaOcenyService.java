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
    public List<KryteriaOcenyDTO> getKryteriaOceny() {
        return kryteriaOcenyRepo.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public KryteriaOcenyDTO getKryteriumOceny(Pracownik pracownik) {
        boolean czyMaTytulNaukowy = czyMaTytulNaukowy(pracownik.getStopienNaukowy().getNazwa());
        boolean czKierownik = czyKierownik(pracownik.getPracownikStanowisko().getNazwa());
        for(KryteriaOcenyDTO kryteriaOceny : getKryteriaOceny()) {
            if(kryteriaOceny.isCzyNaStanowiskuKierowniczym() == czKierownik &&
                kryteriaOceny.isCzyPosiadaStopienNaukowy() == czyMaTytulNaukowy&&
                Objects.equals(kryteriaOceny.getGrupa(), pracownik.getGrupa().getNazwa())) { //TODO:PODEJZANE DZIALANIE
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
    public KryteriaOcenyDTO convertToDTO(KryteriaOceny kryteriaOceny) {
        return KryteriaOcenyDTO.builder()
                .progPozytywnejOcenyDO(kryteriaOceny.getProgPozytywnejOcenyDO())
                .progPozytywnejOcenyNB(kryteriaOceny.getProgPozytywnejOcenyNB())
                .grupa(kryteriaOceny.getGrupa().getNazwa())
                .IdKryterium(kryteriaOceny.getIdKryterium())
                .progOcenyZWyroznieniemDO(kryteriaOceny.getProgOcenyZWyroznieniemDO())
                .progOcenyZWyroznieniemNB(kryteriaOceny.getProgOcenyZWyroznieniemNB())
                .czyPosiadaStopienNaukowy(kryteriaOceny.getCzyPosiadaStopienNaukowy())
                .czyNaStanowiskuKierowniczym(kryteriaOceny.getCzyNaStanowiskuKierowniczym())
                .build();
    }
}
