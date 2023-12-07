package com.example.projektgruptest.service;

import com.example.projektgruptest.model.RodzajDzialalnosci;
import com.example.projektgruptest.repo.RodzajDzialalnosciRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RodzajDzialanosciService {
    private final RodzajDzialalnosciRepo rodzajDzialalnosciRepo;
    public List<RodzajDzialalnosci> getRodzajeDzialanosci(){
        return rodzajDzialalnosciRepo.findAll();
    }
    public RodzajDzialalnosci getRodzajDzialanosci(long id){
       return rodzajDzialalnosciRepo.getReferenceById(id);
    }
}
