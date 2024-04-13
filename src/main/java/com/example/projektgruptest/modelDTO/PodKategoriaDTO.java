package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.osiagniecie.ValidIloscPunktow;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidIloscPunktow
public class PodKategoriaDTO {

    private Long idPodKategorii;
    private Integer maxPunktow;
    private Integer minPunktow;
    private Long idGrupy;
    private String nazwa;
    private Date dataPoczatkowa;
    private Date dataKoncowa;
    private Boolean zarchiwizowana;
}
