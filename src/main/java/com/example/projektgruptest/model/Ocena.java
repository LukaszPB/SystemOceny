package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Oceny")
@Getter
@Setter
public class Ocena {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOceny;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "idPracownika")
    private Long  idPracownika;
    @Column(name = "iloscPunktow")
    private Integer iloscPunktow;
    @Column(name = "data")
    private Date data;
    @Column(name = "idWniosku")
    private Long idWniosku;
    @Column(name = "Wniosek_idWniosku")
    private Long  wniosekIdWniosku;

    //KLUCZE OBCE

    @ManyToOne
    @MapsId("idWniosku")
    @JoinColumn(name = "idWniosku")
    Wniosek wniosek;

    @OneToMany(mappedBy = "ocena")
    Set<Pracownik> pracownikSet;
}
