package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.StopienNaukowy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopienNaukowyRepo extends JpaRepository<StopienNaukowy,Long> {
    StopienNaukowy findByNazwa(String nazwa);
}
