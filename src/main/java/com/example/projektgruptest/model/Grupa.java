package com.example.projektgruptest.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="Grupa")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Grupa {

    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (name = "nazwa")
    private String nazwa;


    //KLUCZE OBCE
    @OneToMany(mappedBy = "grupa")
    private Set<PodKategoria> podKategoriaSet;

    //Najwyżej do zmiany MANY TO MANY
    @OneToMany(mappedBy = "grupa")
    private Set<Pracownik> pracownikSet;
}
