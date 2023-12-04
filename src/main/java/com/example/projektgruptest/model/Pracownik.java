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

    //KLUCZE OBCE
    @ManyToOne
    private RodzajDzialalnosci rodzajDzialalnosci;

    @OneToMany
    private Set<Wniosek> wniosek;

    @ManyToOne
    private PracownikStanowisko pracownikStanowisko;

    @ManyToOne
    private StopienNaukowy stopienNaukowy;
}
