package com.example.projektgruptest.modelDTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WniosekDTO {

    private Long idWniosku;
    private Long idPracownika;
    private Date dataPoczatkowa, dataKoncowa;
    private Long idOceny;
}
