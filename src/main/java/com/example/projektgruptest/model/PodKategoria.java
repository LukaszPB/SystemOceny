package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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
    private Date dataPoczatkowa;
    private Date dataKoncowa;
    private Boolean zarchiwizowana;

    //KLUCZE OBCE
    @ManyToOne
    Grupa grupa;

    @OneToMany(mappedBy = "podKategoria", cascade = CascadeType.REMOVE)
    Set<Osiagniecie> osiagniecieSet;

}
