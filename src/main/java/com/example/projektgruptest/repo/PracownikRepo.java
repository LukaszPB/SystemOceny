package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracownikRepo extends JpaRepository<Pracownik,Long> {
    List<Pracownik> findByPracownikStanowisko_Id(long pracownikStanowiskoId);
    List<Pracownik> findByStopienNaukowy_Id(long stopienNaukowyId);
    List<Pracownik> findByPrzelozonyId(long id);
    List<Pracownik> findByOcenaSet_Id(long idOceny);

    List<Pracownik> findByWydzialKatedra_NazwaWydzialu(String nazwaWydzialu);
    List<Pracownik> findByWydzialKatedra_NazwaKatedry(String nazwaKatedry);

    List<Pracownik> findByOsiagniecieSet_Id(long id);


}
