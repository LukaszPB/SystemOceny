package com.example.projektgruptest.service;


import com.example.projektgruptest.model.Grupa;
import com.example.projektgruptest.model.PodKategoria;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.modelDTO.KryteriaOcenyDTO;
import com.example.projektgruptest.repo.GrupaRepo;
import com.example.projektgruptest.repo.KryteriaOcenyRepo;
import com.example.projektgruptest.repo.PodKategoriaRepo;
import com.example.projektgruptest.repo.PracownikRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GrupaService {
    private final GrupaRepo grupaRepo;
    private final PracownikRepo pracownikRepo;
    private final PodKategoriaRepo podKategoriaRepo;

    public Grupa getGrupa(String nazwaGrupy) {
        return grupaRepo.findByNazwa(nazwaGrupy);
    }



}
