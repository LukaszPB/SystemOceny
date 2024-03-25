package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="HistoriaModyfikacjiOsiagniecia")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaModyfikacjiOsiagniecia {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="imie")
    private String imie;
    @Column(name="nazwisko")
    private String nazwisko;
    @Column(name="idPracownika")
    private Long idPracownika;
    @Column(name="data")
    private Date data;
    @ManyToOne
    private Osiagniecie osiagniecie;

}
