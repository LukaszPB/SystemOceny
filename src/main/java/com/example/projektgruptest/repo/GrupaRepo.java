package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Grupa;
import com.example.projektgruptest.model.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupaRepo extends JpaRepository<Grupa,Long> {
    Grupa findByNazwa(String nazwa);

}
