package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime poczatek;
    @Column(name = "koniec")
    private LocalDateTime koniec;

    //KLUCZE OBCE
    @OneToMany(mappedBy = "okresRozliczeniowy", cascade = CascadeType.REMOVE)
    Set<Wniosek> wniosekSet;

    @Override
    public String toString() {
        return "OkresRozliczeniowy id " + idOkresu + ":\n" +
                "   " + poczatek + "\n" +
                "   " + koniec + "\n";
    }
}
