package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="PodKategorie")
@Getter
@Setter
public class PodKategoria {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPodKategorii;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "minPunktow")
    private Integer minPunktow;
    @Column(name = "maxPunktow")
    private Integer maxPunktow;

    //KLUCZE OBCE
    @ManyToOne
    KategoriaOsiagniec kategoriaOsiagniec;

    @OneToMany(mappedBy = "podKategoria", cascade = CascadeType.REMOVE)
    Set<Osiagniecie> osiagniecieSet;
}
