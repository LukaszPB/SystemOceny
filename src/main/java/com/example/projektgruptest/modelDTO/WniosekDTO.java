package com.example.projektgruptest.modelDTO;

import lombok.*;

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
    private String dataPoczatkowa, dataKoncowa;
    private long idOceny;
    private List<Long> listaIdOsiagniec;



}
