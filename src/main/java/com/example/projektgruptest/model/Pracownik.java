package com.example.projektgruptest.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="Pracownicy")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pracownik {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    private Boolean czyMoznaOcenic;

    //KLUCZE OBCE
    @ManyToOne
    private Grupa grupa;

    @OneToMany(mappedBy = "pracownik" , cascade = CascadeType.REMOVE)
    private Set<Osiagniecie> osiagniecieSet;

    @OneToMany(mappedBy = "pracownik", cascade = CascadeType.REMOVE)
    private Set<Ocena> ocenaSet;

    @ManyToOne
    private PracownikStanowisko pracownikStanowisko;

    @ManyToOne
    private StopienNaukowy stopienNaukowy;

    @ManyToOne
    private WydzialKatedra wydzialKatedra;
}
