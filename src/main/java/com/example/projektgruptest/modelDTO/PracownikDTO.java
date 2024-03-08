package com.example.projektgruptest.modelDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PracownikDTO {
    @NotEmpty
    private Long idPracownika;
    private String imie;
    private String nazwisko;
    private String emailSluzbowy;
    private String stopienNaukowyNazwa;
    private String stanowiskoNazwa;
    @NotBlank
    private String grupa;
    //Lista osiagniec pracownika oraz jego ocen
    private Long[] osiagniecieSet;
    private Long[] ocenaSet;
}
