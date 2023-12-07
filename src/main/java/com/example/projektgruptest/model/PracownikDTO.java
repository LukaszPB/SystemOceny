package com.example.projektgruptest.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracownikDTO {
    private Long idPracownika;
    private String imie;
    private String nazwisko;
    private String emailSluzbowy;
    private String stopienNaukowyNazwa;
    private String stanowiskoNazwa;
    private String rodzajDzialalnosciNazwa;
}
