package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.RodzajDzialalnosci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RodzajDzialalnosciRepo extends JpaRepository<RodzajDzialalnosci,Long> {
    RodzajDzialalnosci findByNazwa(String nazwa);
}
