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

    @Column(name = "dataPoczatkowa")
    private Date dataPoczatkowa;
    @Column(name = "dataKoncowa")
    private Date dataKoncowa;

    //KLUCZE OBCE
    @ManyToOne
    Pracownik pracownik;
}
