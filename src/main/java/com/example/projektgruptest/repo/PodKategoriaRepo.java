package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.PodKategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodKategoriaRepo extends JpaRepository<PodKategoria,Long> {
    List<PodKategoria> findByKategoriaOsiagniecIdKategoriaOsiagniec(long id);
    PodKategoria findByNazwa(String nazwa);
}
