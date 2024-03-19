package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.ValidRolaExist;
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
    @ValidRolaExist
    private String rola;
    private Long idPracownika;
}
