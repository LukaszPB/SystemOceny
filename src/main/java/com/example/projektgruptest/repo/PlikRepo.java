package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.model.Plik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlikRepo extends JpaRepository<Plik,Long> {
    List<Plik> findByOsiagniecie(Osiagniecie osiagniecie);
}
