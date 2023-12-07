package com.example.projektgruptest.service;

import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.repo.PodKategoriaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PodKategorieService {
    private final PodKategoriaRepo podKategoriaRepo;

    public List<PodKategoria> getPodKategorie() {
        return podKategoriaRepo.findAll();
    }
    public PodKategoria getPodkategoria(long id) {
        return podKategoriaRepo.getReferenceById(id);
    }
    public PodKategoria getPodkategoria(String nazwa) {
        return podKategoriaRepo.findByNazwa(nazwa);
    }
    public List<PodKategoria> getPodKategorieKategori(long id) {
        return podKategoriaRepo.findByKategoriaOsiagniecIdKategoriaOsiagniec(id);
    }
    public void addPodkategoria(PodKategoria podKategoria) {
        podKategoriaRepo.save(podKategoria);
    }
    public void deletePodkategoria(PodKategoria podKategoria) {
        podKategoriaRepo.delete(podKategoria);
    }
}
