package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.PracownikStanowisko;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracownikStanowiskoRepo extends JpaRepository<PracownikStanowisko,Long> {
    //    @Modifying
//    @Transactional
//    @Query("DELETE FROM Osiagniecie o WHERE o.wniosek.pracownik.idPracownika = :pracownikId")
//    void deleteOsiagniecieByPracownikId(@Param("pracownikId") Long pracownikId);
    PracownikStanowisko findByNazwa(String nazwa);
}
