package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.ValidGrupaExist;
import com.example.projektgruptest.validator.podkategoria.ValidDatesOrderPK;
import com.example.projektgruptest.validator.podkategoria.ValidProgiPunktow;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidProgiPunktow
@ValidDatesOrderPK
public class PodKategoriaDTO {

    private Long idPodKategorii;
    private Integer maxPunktow;
    private Integer minPunktow;
    @ValidGrupaExist
    private Long idGrupy;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 1, max = 450, message = "Name length must be between 1 and 250 characters")
    private String nazwa;
    @NotNull
    private Date dataPoczatkowa;
    @NotNull
    private Date dataKoncowa;
    private Boolean zarchiwizowana;
}
