package com.example.projektgruptest.service;

import com.example.projektgruptest.model.StopienNaukowy;
import com.example.projektgruptest.repo.StopienNaukowyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StopienNaukowyService {
    private final StopienNaukowyRepo stopienNaukowyRepo;
    public List<StopienNaukowy> getStopnieNaukowe(){
       return stopienNaukowyRepo.findAll();
    }
    public StopienNaukowy getStopienNaukowy(long id){
      return stopienNaukowyRepo.getReferenceById(id);
    }
    public StopienNaukowy getStopienNaukowy(String nazwa){
        return stopienNaukowyRepo.findByNazwa(nazwa);
    }
}
