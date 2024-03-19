package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Osiagniecie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsiagniecieRepo extends JpaRepository<Osiagniecie,Long> {

    List<Osiagniecie> findByPracownik_Id(long id);
    List<Osiagniecie> findByOcena_Id(Long id);
}
