package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

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
    @ManyToOne
    Ocena ocena;
    @OneToMany(mappedBy = "osiagniecie" , cascade = CascadeType.REMOVE)
    private Set<HistoriaModyfikacjiOsiagniecia>    HistoriaModyfikacjiOsiagnieciaSet;
    @OneToMany(mappedBy = "osiagniecie", cascade = CascadeType.REMOVE)
    private Set<Plik> plikSet;
}
