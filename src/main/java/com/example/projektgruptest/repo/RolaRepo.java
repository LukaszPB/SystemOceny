package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.Rola;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolaRepo extends JpaRepository<Rola,Long> {
}
