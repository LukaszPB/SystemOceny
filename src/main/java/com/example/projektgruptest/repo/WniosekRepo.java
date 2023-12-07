package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Wniosek;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WniosekRepo extends JpaRepository<Wniosek,Long> {
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Wniosek w WHERE w.pracownik.idPracownika = :pracownikId")
//    void deleteWniosekByPracownikId(@Param("pracownikId") Long pracownikId);
    List<Wniosek> findByPracownikIdPracownika(long pracownikId);
}
