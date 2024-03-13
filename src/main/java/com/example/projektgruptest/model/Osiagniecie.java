package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="Osiagniecia")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Osiagniecie {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "iloscPunktow")
    private Integer iloscPunktow;
    @Column(name = "data")
    private Date data;
    @Column(name = "zatwierdzone")
    private Boolean zatwierdzone;
    @Column(name = "zarchiwizowane")
    private Boolean zarchiwizowane;
    // KLUCZE OBCE
    @ManyToOne
    PodKategoria podKategoria;
    @ManyToOne
    Pracownik pracownik;
}
