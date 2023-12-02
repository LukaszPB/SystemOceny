package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Uzytkownik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String haslo;

    @ManyToOne(optional = false)
    private Rola rola;

    @OneToOne(fetch = FetchType.EAGER)
    private Pracownik pracownik;
}
