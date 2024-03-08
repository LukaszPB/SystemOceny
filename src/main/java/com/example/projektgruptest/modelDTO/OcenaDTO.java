package com.example.projektgruptest.modelDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OcenaDTO {

    @NotEmpty
    private long idOceny;

    private String nazwa;

    private int iloscPunktow;
    @NotEmpty
    private Date dataPoczatkowa;
    @NotEmpty
    private Date dataKoncowa;

    private boolean czyZatwierdzona;

}
