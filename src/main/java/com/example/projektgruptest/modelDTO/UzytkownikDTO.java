package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.model.auth.Rola;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UzytkownikDTO {

    private long id;

    private String login;
    private String haslo;

    private String nazwaRoli;
    private long idPracownika;
    private String imiePracownika;
    private String nazwiskoPracownika;
}
