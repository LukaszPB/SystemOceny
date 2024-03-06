package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="OkresRozliczeniowy")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    @OneToMany(mappedBy = "okresRozliczeniowy", cascade = CascadeType.REMOVE)
    Set<Wniosek> wniosekSet;
}
