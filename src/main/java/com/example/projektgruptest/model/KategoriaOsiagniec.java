package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="KategorieOsiagniec")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
