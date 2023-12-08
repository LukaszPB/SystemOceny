package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name="Wnioski")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wniosek {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idWniosku;

    //KLUCZE OBCE
    @OneToMany(mappedBy = "wniosek", cascade = CascadeType.REMOVE)
    Set<Osiagniecie> osiagniecieSet;

    @ManyToOne
    Pracownik pracownik;

    @ManyToOne
    OkresRozliczeniowy okresRozliczeniowy;

    @OneToOne(mappedBy = "wniosek", cascade = CascadeType.REMOVE)
    private Ocena ocena;

    @Override
    public String toString() {
        return "Wniosek id " + idWniosku + ":\n" +
                "   " + pracownik + "\n" +
                "   " + okresRozliczeniowy + "\n";
    }
}
