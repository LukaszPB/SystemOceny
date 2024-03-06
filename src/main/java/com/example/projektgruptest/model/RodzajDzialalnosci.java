package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="RodzajeDzialalnosci")
@Getter
@Setter
public class RodzajDzialalnosci {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRodzajDzialalnosci;
    @Column(name = "nazwa")
    private String nazwa;

    //KLUCZE OBCE
    @OneToMany(mappedBy = "rodzajDzialalnosci")
    Set<KryteriaOceny> kryteriaOcenySet;

    @OneToMany(mappedBy = "rodzajDzialalnosci")
    Set<KategoriaOsiagniec> kategoriaOsiagniecSet;

    @OneToMany(mappedBy = "rodzajDzialalnosci")
    Set<Pracownik> pracownikSet;
}
