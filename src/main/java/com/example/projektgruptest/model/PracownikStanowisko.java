package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="PracownikStanowiska")
@Getter
@Setter
public class PracownikStanowisko {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStanowiska;
    @Column(name = "nazwa")
    private String nazwa;

    //KLUCZ OBCY
    @OneToMany(mappedBy = "pracownikStanowisko")
    Set<Pracownik> pracownikSet;
    @Override
    public String toString() {
        return "Stanowisko id " + idStanowiska + ":\n" +
                "   " + nazwa + "\n";
    }
}
