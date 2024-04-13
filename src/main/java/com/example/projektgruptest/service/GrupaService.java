package com.example.projektgruptest.service;


import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Grupa;
import com.example.projektgruptest.repo.GrupaRepo;
import com.example.projektgruptest.repo.PodKategoriaRepo;
import com.example.projektgruptest.repo.PracownikRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GrupaService {
    private final GrupaRepo grupaRepo;
    private final PracownikRepo pracownikRepo;
    private final PodKategoriaRepo podKategoriaRepo;

    public Grupa getGrupa(String nazwaGrupy) {
        return grupaRepo.findByNazwa(nazwaGrupy);
    }
    public Grupa getGrupa(Long id) {
        return grupaRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Grupa o id: " + id + " nie istnieje"));
    }


}
