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
    RodzajDzialalnosci rodzajDzialalnosci;

    @OneToMany(mappedBy = "kategoriaOsiagniec", cascade = CascadeType.REMOVE)
    Set<PodKategoria> podKategoriaSet;
    @Override
    public String toString() {
        return "Kategoria Osiągnięć id " +idKategoriaOsiagniec + ":\n" +
                "   " + nazwaKategorii + "\n";
    }
}
