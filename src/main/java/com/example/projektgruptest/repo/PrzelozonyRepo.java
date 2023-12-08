package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.PracownikStanowisko;
import com.example.projektgruptest.model.Przelozony;
import com.example.projektgruptest.model.RodzajDzialalnosci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrzelozonyRepo extends JpaRepository<Przelozony,Long> {

    Przelozony findByNazwisko(String nazwisko);
    //List<Pracownik> findByPrzelozony_IdPracownika(long idPracownika);

}
