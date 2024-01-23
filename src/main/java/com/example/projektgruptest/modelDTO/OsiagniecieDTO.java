package com.example.projektgruptest.modelDTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OsiagniecieDTO {
    //dto
    private long idOsiagniecia;

    private String nazwa;

    private int iloscPunktow;

    private Date data;

    private boolean czyZatwierdzone;

    private String podKategoriaNazwa;

    private long idWniosku;
}
