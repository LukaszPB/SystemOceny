package com.example.projektgruptest.modelDTO;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WniosekDTO {

    private long idWniosku;
    //PracownikDTO pracownikDTO;
    private long idPracownika;
    private Date dataPoczatkowa, dataKoncowa;
    private Long idOceny;
    private List<Long> listaIdOsiagniec;



}
