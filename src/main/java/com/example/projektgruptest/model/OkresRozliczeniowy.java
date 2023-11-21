package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="OkresRozliczeniowy")
@Getter
@Setter
public class OkresRozliczeniowy {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOkresu;
    @Column(name = "poczatek")
    private Date poczatek;
    @Column(name = "koniec")
    private Date koniec;

    //KLUCZE OBCE
    @OneToMany(mappedBy = "okresRozliczeniowy")
    Set<Wniosek> wniosekSet;


}
