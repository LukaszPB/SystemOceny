package com.example.projektgruptest.service;

import com.example.projektgruptest.model.*;
import com.example.projektgruptest.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrzelozonyService {

    private final PrzelozonyRepo przelozonyRepo;
    public List<Przelozony> getPrzelozeni() {
        return przelozonyRepo.findAll();
    }
    public Przelozony getPrzelozony(long id) {return przelozonyRepo.getReferenceById(id);}

    //znalezienie konkretnego podwładnego po jego id czy podlega pod danego przelozonego
//    public List<Pracownik> getPrzelozonyPodwladny(long id) {
//        return przelozonyRepo.findByPrzelozony_IdPracownika(id);
//
//    }
    public void addPrzelozony(Przelozony przelozony) {przelozonyRepo.save(przelozony);}
    public void deletePrzelozony(Przelozony przelozony) {przelozonyRepo.delete(przelozony);}


}
