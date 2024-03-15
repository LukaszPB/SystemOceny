package com.example.projektgruptest.model;

import com.example.projektgruptest.enums.WynikOceny;
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
    private Long id;
    @Enumerated(EnumType.STRING)
    private WynikOceny wynikOceny;
    @Column(name = "dataPoczatkowa")
    private Date dataPoczatkowa;
    @Column(name = "dataKoncowa")
    private Date dataKoncowa;
    @Column(name = "zatwierdzona")
    private Boolean zatwierdzona;

    //KLUCZE OBCE
    @ManyToOne
    Pracownik pracownik;
}
