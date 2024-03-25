package com.example.projektgruptest.repo;


import com.example.projektgruptest.model.HistoriaModyfikacjiOsiagniecia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HistoriaModyfikacjiOsiagniecRepo extends JpaRepository<HistoriaModyfikacjiOsiagniecia,Long> {
}
