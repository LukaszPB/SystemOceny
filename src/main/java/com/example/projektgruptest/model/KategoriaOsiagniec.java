package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="KategorieOsiagniec")
@Getter
@Setter
public class KategoriaOsiagniec {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idKategoriaOsiagniec;
    @Column(name = "nazwaKategorii")
    private String nazwaKategorii;

    //KLUCZE OBCE
    @ManyToOne
    @MapsId("idRodzajDzialalnosci")
    @JoinColumn(name = "idRodzajDzialalnosci")
    RodzajDzialalnosci rodzajDzialalnosci;

    @OneToMany(mappedBy = "kategoriaOsiagniec")
    Set<PodKategoria> podKategoriaSet;

}
