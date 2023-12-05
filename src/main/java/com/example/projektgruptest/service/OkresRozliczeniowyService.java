package com.example.projektgruptest.service;

import com.example.projektgruptest.model.OkresRozliczeniowy;
import com.example.projektgruptest.repo.OkresRozliczeniowyRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OkresRozliczeniowyService {
    private final OkresRozliczeniowyRepo okresRozliczeniowyRepo;
    public OkresRozliczeniowy getOkresRozliczeniowy(long id) {
        return okresRozliczeniowyRepo.getReferenceById(id);
    }
    public List<OkresRozliczeniowy> getOkresyRozliczeniowe() {
        return okresRozliczeniowyRepo.findAll();
    }
    public void addOkresRozliczeniowy(OkresRozliczeniowy okres) {
        okresRozliczeniowyRepo.save(okres);
    }
    public void deleteOkresRozliczeniowy(OkresRozliczeniowy okres) {
        okresRozliczeniowyRepo.delete(okres);
    }
}
