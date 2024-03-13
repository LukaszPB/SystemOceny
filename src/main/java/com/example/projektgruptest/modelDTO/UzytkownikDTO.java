package com.example.projektgruptest.modelDTO;

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
    private String rola;
    private Long idPracownika;
}
