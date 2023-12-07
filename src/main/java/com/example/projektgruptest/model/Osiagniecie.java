package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="Osiagniecia")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Osiagniecie {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOsiagniecia;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "iloscPunktow")
    private Integer iloscPunktow;
    @Column(name = "data")
    private Date data;
    @Column(name = "czyZatwierdzone")
    private Boolean czyZatwierdzone;

    // KLUCZE OBCE
    @ManyToOne
    PodKategoria podKategoria;

    @ManyToOne
    Wniosek wniosek;

    @Override
    public String toString() {
        return "Osiagnięcie id " + idOsiagniecia + ":\n" +
                "   " + nazwa + "\n" +
                "   " + iloscPunktow + "\n" +
                "   " + data + "\n" +
                "   " + czyZatwierdzone + "\n";
    }
}
