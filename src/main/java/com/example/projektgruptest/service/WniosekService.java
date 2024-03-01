package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Wniosek;
import com.example.projektgruptest.repo.WniosekRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WniosekService {
    private final WniosekRepo wniosekRepo;
    public Wniosek getWniosek(long id) {
        return wniosekRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Wniosek o podanym id nie zostal znaleziony: " + id));
    }
    public List<Wniosek> getWnioski() {
        return wniosekRepo.findAll();
    }
    public List<Wniosek> getWnioskiPraconika(long id) {
        return wniosekRepo.findByPracownikIdPracownika(id);
    }
    public void addWniosek(Wniosek wniosek) {
        wniosekRepo.save(wniosek);
    }
    public void deleteWniosek(Wniosek wniosek) {
        wniosekRepo.delete(wniosek);
    }
}
