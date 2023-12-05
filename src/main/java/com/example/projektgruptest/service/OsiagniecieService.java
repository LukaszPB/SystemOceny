package com.example.projektgruptest.service;

import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.repo.OsiagniecieRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OsiagniecieService {
    private final OsiagniecieRepo osiagniecieRepo;
    public List<Osiagniecie> getOsiagniecia() {
        return osiagniecieRepo.findAll();
    }
    public Osiagniecie getOsiagniecie(long id) {
        return osiagniecieRepo.getReferenceById(id);
    }
    public List<Osiagniecie> getOsiagnieciaPracownika(long id) {
        return osiagniecieRepo.findByWniosekPracownikIdPracownika(id);
    }
    public List<Osiagniecie> getOsiagnieciaWniosku(long id) {
        return osiagniecieRepo.findByWniosekIdWniosku(id);
    }
    public void addOsiagniecie(Osiagniecie osiagniecie) {
        osiagniecieRepo.save(osiagniecie);
    }
    public void deleteOsiagniecie(Osiagniecie osiagniecie) {
        osiagniecieRepo.delete(osiagniecie);
    }
}
