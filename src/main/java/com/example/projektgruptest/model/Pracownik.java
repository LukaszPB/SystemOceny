package com.example.projektgruptest.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="Pracownicy")
@Getter
@Setter
public class Pracownik {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPracownika;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "Email")
    private String emailSluzbowy;
    @Column(name = "idRodzajDzialalnosci")
    private Long idRodzajDzialalnosci;

    @Column(name = "idStopniaNaukowego")
    private Long idStopniaNaukowego;
    @Column(name = "idStanowiska")
    private Long idStanowiska;
    @Column(name = "idRoli")
    private Long idRoli;

    //KLUCZE OBCE

    @OneToMany(mappedBy = "pracownik")
    Set<Osiagniecie> osiagniecieSet;

    @ManyToOne
    @MapsId("idRodzajDzialalnosci")
    @JoinColumn(name="idRodzajDzialalnosci")
    RodzajDzialalnosci rodzajDzialalnosci;

    @ManyToOne
    @MapsId("idWniosku")
    @JoinColumn(name = "idWniosku")
    Wniosek wniosek;

    @ManyToOne
    @MapsId("idOceny")
    @JoinColumn(name = "idOceny")
    Ocena ocena;

    @ManyToOne
    @MapsId("idRoli")
    @JoinColumn(name = "idRoli")
    Rola rola;

    @ManyToOne
    @MapsId("idStanowiska")
    @JoinColumn(name = "idStanowiska")
    PracownikStanowisko pracownikStanowisko;

    @ManyToOne
    @MapsId("idStopniaNaukowego")
    @JoinColumn(name = "idStopniaNaukowego")
    StopienNaukowy stopienNaukowy;
}
