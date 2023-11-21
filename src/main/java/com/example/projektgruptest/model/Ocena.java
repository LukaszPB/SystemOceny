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
    @Column(name = "iloscPunktow")
    private Integer iloscPunktow;
    @Column(name = "data")
    private Date data;

    //KLUCZE OBCE
    @ManyToOne
    Wniosek wniosek;
}
