package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="Wnioski")
@Getter
@Setter
public class Wniosek {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idWniosku;

    //KLUCZE OBCE
    @OneToMany(mappedBy = "wniosek")
    Set<Osiagniecie> osiagniecieSet;

    @ManyToOne
    Pracownik pracownik;

    @ManyToOne
    OkresRozliczeniowy okresRozliczeniowy;

    @OneToMany(mappedBy = "wniosek")
    Set<Ocena> ocenaSet;
}
