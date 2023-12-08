package com.example.projektgruptest.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Przelozeni")
@Getter
@Setter
public class Przelozony {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idPrzelozonego;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "Email")
    private String emailSluzbowy;

//    @OneToMany(mappedBy = "przelozony", cascade = CascadeType.REMOVE)
//    Set<Pracownik> pracownikSet;

    @Override
    public String toString() {
        return "Przelozony id " + idPrzelozonego + ":\n" +
                "   " + imie + "\n" +
                "   " + nazwisko + "\n" +
                "   " + emailSluzbowy + "\n";
    }
}
