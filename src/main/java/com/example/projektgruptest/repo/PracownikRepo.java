package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracownikRepo extends JpaRepository<Pracownik,Long> {
    List<Pracownik> findByPracownikStanowisko_IdStanowiska(long pracownikStanowiskoId);
    List<Pracownik> findByStopienNaukowy_IdStopniaNaukowego(long stopienNaukowyId);
    List<Pracownik> findByPrzelozonyIdPracownika(long id);
    List<Pracownik> findByOcenaSet_IdOceny(long idOceny);

    //List<Pracownik> findByOcenaSet_IdPracownika(long idPracownika);

    List<Pracownik> findByOsiagniecieSet_IdOsiagniecia(long idOsiagniecia);


}
