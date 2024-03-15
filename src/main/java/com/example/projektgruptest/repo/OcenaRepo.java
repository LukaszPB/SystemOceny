package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcenaRepo extends JpaRepository<Ocena,Long> {


    List<Ocena> findByPracownik_Id(long idPracownika);

}
