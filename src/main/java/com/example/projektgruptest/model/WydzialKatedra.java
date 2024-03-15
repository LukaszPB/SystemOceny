package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="WydzialKatedra")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WydzialKatedra {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazwaWydzialu;
    private String nazwaKatedry;

    @OneToMany(mappedBy = "wydzialKatedra")
    Set<Pracownik> PracownikSet;
}
