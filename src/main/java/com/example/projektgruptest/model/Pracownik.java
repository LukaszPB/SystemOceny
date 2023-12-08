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
    private Long idPracownika;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "Email")
    private String emailSluzbowy;

    //KLUCZE OBCE
    @ManyToOne
    @JoinColumn(name = "idPrzelozony", nullable = true)
    private Pracownik przelozony;
    @ManyToOne
    private RodzajDzialalnosci rodzajDzialalnosci;

    @OneToMany(mappedBy = "pracownik", cascade = CascadeType.REMOVE)
    private Set<Wniosek> wniosek;

    @ManyToOne
    private PracownikStanowisko pracownikStanowisko;

    @ManyToOne
    private StopienNaukowy stopienNaukowy;

    @Override
    public String toString() {
        return "Pracownik id " + idPracownika + ":\n" +
                "   " + stopienNaukowy.getNazwa() + "\n" +
                "   " + rodzajDzialalnosci.getNazwa() + "\n" +
                "   " + stopienNaukowy.getNazwa() + "\n" +
                "   " + imie + "\n" +
                "   " + nazwisko + "\n" +
                "   " + emailSluzbowy + "\n";
    }
}
