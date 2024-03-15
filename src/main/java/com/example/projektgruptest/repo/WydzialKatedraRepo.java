package com.example.projektgruptest.repo;

import com.example.projektgruptest.model.WydzialKatedra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WydzialKatedraRepo extends JpaRepository<WydzialKatedra, Long> {

    List<WydzialKatedra> findWydzialKatedraByNazwaKatedry(String nazwaKatedry);

    List<WydzialKatedra> findWydzialKatedraByNazwaWydzialu(String nazwaWydzialu);

}
