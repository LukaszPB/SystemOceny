package com.example.projektgruptest.model.auth;

import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.validator.ValidPracownikExist;
import com.example.projektgruptest.validator.ValidUzytkownikExist;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
