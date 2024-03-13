package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Ocena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcenaRepo extends JpaRepository<Ocena,Long> {
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Ocena o WHERE o.wniosek.pracownik.idPracownika = :pracownikId")
//    void deleteOcenaByPracownikId(@Param("pracownikId") Long pracownikId);
    //List<Ocena> findByWniosekPracownikIdPracownika(long pracownikId);

    List<Ocena> findByPracownik_Id(long idPracownika);

}
