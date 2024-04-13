package com.example.projektgruptest.service;

import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.modelDTO.PodKategoriaDTO;
import com.example.projektgruptest.repo.PodKategoriaRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PodKategorieService {
    private final PodKategoriaRepo podKategoriaRepo;
    private final GrupaService grupaService;

    public List<PodKategoria> getPodKategorie() {
        return podKategoriaRepo.findAll();
    }
    public PodKategoria getPodkategoria(Long id) {
        return podKategoriaRepo.getReferenceById(id);
    }
    public PodKategoria getPodkategoria(String nazwa) {
        return podKategoriaRepo.findByNazwa(nazwa);
    }
    public List<PodKategoria> getPodkategorieByGrupa(String nazwa) {
        return podKategoriaRepo.findPodKategoriaByGrupa_Nazwa(nazwa);
    }
    @Transactional
    public void addPodkategoria(PodKategoriaDTO podKategoriaDTO) {
        podKategoriaRepo.save(buildPodkategoria(podKategoriaDTO));
    }
    public PodKategoria buildPodkategoria(PodKategoriaDTO podKategoriaDTO) {
        return PodKategoria.builder()
                .nazwa(podKategoriaDTO.getNazwa())
                .maxPunktow(podKategoriaDTO.getMaxPunktow())
                .minPunktow(podKategoriaDTO.getMinPunktow())
                .dataPoczatkowa(podKategoriaDTO.getDataPoczatkowa())
                .dataKoncowa(podKategoriaDTO.getDataKoncowa())
                .zarchiwizowana(podKategoriaDTO.getZarchiwizowana())
                .grupa(grupaService.getGrupa(podKategoriaDTO.getIdGrupy()))
                .build();
    }
    @Transactional
    public void deletePodkategoria(long idPodkategorii){
        podKategoriaRepo.deleteById(idPodkategorii);
    }
    @Transactional
    public void zarchiwizuj(Date date) {
        getPodKategorie().stream()
                .filter(podKategoria -> !podKategoria.getZarchiwizowana() &&
                                date.after(podKategoria.getDataKoncowa()))
                .forEach(podKategoria -> {
                    podKategoria.setZarchiwizowana(true);
                    podKategoriaRepo.save(podKategoria);
                });
    }
    public List<PodKategoriaDTO> convertListToDTO(List<PodKategoria> podKategoriaList) {
        return podKategoriaList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PodKategoriaDTO convertToDTO(PodKategoria podKategoria) {
        return PodKategoriaDTO.builder()
                .idPodKategorii(podKategoria.getIdPodKategorii())
                .minPunktow(podKategoria.getMinPunktow())
                .maxPunktow(podKategoria.getMaxPunktow())
                .idGrupy(podKategoria.getGrupa().getId())
                .nazwa(podKategoria.getNazwa())
                .dataPoczatkowa(podKategoria.getDataPoczatkowa())
                .dataKoncowa(podKategoria.getDataKoncowa())
                .zarchiwizowana(podKategoria.getZarchiwizowana())
                .build();
    }


}
