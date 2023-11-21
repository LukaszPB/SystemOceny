package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="Osiagniecia")
@Getter
@Setter
public class Osiagniecie {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOsiagniecia;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "iloscPunktow")
    private Integer iloscPunktow;
    @Column(name = "idPracownika")
    private Long idPracownika;
    @Column(name = "idPodKategorii")
    private Long idPodKategorii;
    @Column(name = "data")
    private Date data;
    @Column(name = "czyZatwierdzone")
    private Boolean czyZatwierdzone;
    @Column(name = "idWniosku")
    private Long idWniosku;

    //TODO: KLUCZE OBCE

    @ManyToOne
    @MapsId("idPodKategorii")
    @JoinColumn(name = "idPodKategorii")
    PodKategoria podKategoria;

    @ManyToOne
    @MapsId("idPracownika")
    @JoinColumn(name = "idPracownika")
    Pracownik pracownik;

    @ManyToOne
    @MapsId("idWniosku")
    @JoinColumn(name = "idWniosku")
    Wniosek wniosek;


}
