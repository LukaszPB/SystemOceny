package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.model.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private List<Long> listaIdOsiagniec, listaIdOcen;



}
