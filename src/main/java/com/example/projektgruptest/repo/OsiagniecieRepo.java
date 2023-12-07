package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Osiagniecie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OsiagniecieRepo extends JpaRepository<Osiagniecie,Long> {
//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Osiagniecie o WHERE o.wniosek.pracownik.idPracownika = :pracownikId")
//    void deleteOsiagniecieByPracownikId(@Param("pracownikId") Long pracownikId);
    List<Osiagniecie> findByWniosekPracownikIdPracownika(long pracownikId);
    List<Osiagniecie> findByWniosekIdWniosku(long pracownikwniosekId);
}
