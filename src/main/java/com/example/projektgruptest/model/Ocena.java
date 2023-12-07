package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="Oceny")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ocena {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOceny;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "iloscPunktow")
    private Integer iloscPunktow;
    @Column(name = "data")
    private Date data;

    //KLUCZE OBCE
    @ManyToOne
    Wniosek wniosek;

    public String toString() {
        return "Ocena id " + idOceny + ":\n" +
                "   " + nazwa + "\n" +
                "   " + iloscPunktow + "\n" +
                "   " + data + "\n";
    }
}
