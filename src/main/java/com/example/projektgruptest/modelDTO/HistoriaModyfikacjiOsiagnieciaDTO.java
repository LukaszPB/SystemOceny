package com.example.projektgruptest.modelDTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoriaModyfikacjiOsiagnieciaDTO {
    private Long id;
    private String imie;
    private String nazwisko;
    private Long idPracownika;
    private Date data;
}
