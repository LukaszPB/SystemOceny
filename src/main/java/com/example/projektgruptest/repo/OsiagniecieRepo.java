package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Osiagniecie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsiagniecieRepo extends JpaRepository<Osiagniecie,Long> {
    //List<Osiagniecie> findByWniosekPracownikIdPracownika(long pracownikId);
    //List<Pracownik> findByPracownik_IdPracownika(long pracownikOcenaId);
    List<Osiagniecie> findByPracownik_Id(long id);
    //Musimy znaleźć osiągnięcia na podstawie idOceny
}
