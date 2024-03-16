package com.example.projektgruptest.service;

import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.modelDTO.PodKategoriaDTO;
import com.example.projektgruptest.repo.PodKategoriaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PodKategorieService {
    private final PodKategoriaRepo podKategoriaRepo;

    public List<PodKategoria> getPodKategorie() {
        return podKategoriaRepo.findAll();
    }
    public List<PodKategoriaDTO> getPodKategorieDTO(){
        List<PodKategoria> podKategoriaList = podKategoriaRepo.findAll();
        return convertListToDTO(podKategoriaList);
    }

    public PodKategoriaDTO getPodKategoriaDTO(long idPodkategorii){
        PodKategoria podKategoria = podKategoriaRepo.findByIdPodKategorii(idPodkategorii);
        return convertToDTO(podKategoria);
    }

    public PodKategoria getPodkategoria(long id) {
        return podKategoriaRepo.getReferenceById(id);
    }
    public PodKategoria getPodkategoria(String nazwa) {
        return podKategoriaRepo.findByNazwa(nazwa);
    }
    public void addPodkategoria(PodKategoria podKategoria) {
        podKategoriaRepo.save(podKategoria);
    }
    public void deletePodkategoria(PodKategoria podKategoria) {
        podKategoriaRepo.delete(podKategoria);
    }
    public void deletePodkategoria(long idPodkategorii){podKategoriaRepo.deleteById(idPodkategorii);}

   public List<PodKategoria> getPodkategorieByGrupa(String nazwa)
   {
       return podKategoriaRepo.findPodKategoriaByGrupa_Nazwa(nazwa);
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
                .build();
    }


}
