package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.ValidIloscPunktow;
import lombok.*;

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


}
