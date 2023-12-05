package com.example.projektgruptest.service;

import com.example.projektgruptest.model.KategoriaOsiagniec;
import com.example.projektgruptest.repo.KategoriaOsiagniecRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KategoriaService {
    private final KategoriaOsiagniecRepo kategoriaOsiagniecRepo;
    public List<KategoriaOsiagniec> getKategorie() {
        return kategoriaOsiagniecRepo.findAll();
    }
    public KategoriaOsiagniec getKategoria(long id) {
        return kategoriaOsiagniecRepo.getReferenceById(id);
    }
    public void addKategoria(KategoriaOsiagniec kategoriaOsiagniec) {
        kategoriaOsiagniecRepo.save(kategoriaOsiagniec);
    }
    public void deleteKategoria(KategoriaOsiagniec kategoriaOsiagniec) {
        kategoriaOsiagniecRepo.delete(kategoriaOsiagniec);
    }
}
