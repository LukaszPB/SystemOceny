package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="Wnioski")
@Getter
@Setter
public class Wniosek {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idWniosku;
    @Column(name = "idPracownika")
    private Long idPracownika;
    @Column(name = "idOkresu")
    private Long idOkresu;


    //KLUCZE OBCE
    @OneToMany(mappedBy = "wniosek")
    Set<Osiagniecie> osiagniecieSet;

    @OneToMany(mappedBy = "wniosek")
    Set<Pracownik> pracownikSet;

    @ManyToOne
    @MapsId("idOkresu")
    @JoinColumn(name = "idOkresu")
    OkresRozliczeniowy okresRozliczeniowy;

    @OneToMany(mappedBy = "wniosek")
    Set<Ocena> ocenaSet;

}
