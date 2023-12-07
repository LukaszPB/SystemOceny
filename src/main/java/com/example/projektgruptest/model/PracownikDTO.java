package com.example.projektgruptest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PracownikDTO {
    private Long idPracownika;
    private String imie;
    private String nazwisko;
    private String emailSluzbowy;
}
