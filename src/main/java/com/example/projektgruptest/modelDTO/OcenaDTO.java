package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.EditValidationGrup;
import com.example.projektgruptest.validator.ValidPracownikExist;
import com.example.projektgruptest.validator.ocena.ValidDatesOrder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidDatesOrder(groups = EditValidationGrup.class)
public class OcenaDTO {
    private long id;
    @NotEmpty(groups = EditValidationGrup.class)
    private String nazwa;
    private int iloscPunktow;
    @NotNull(groups = EditValidationGrup.class)
    private Date dataPoczatkowa;
    @NotNull(groups = EditValidationGrup.class)
    private Date dataKoncowa;
    @ValidPracownikExist
    private Long idPracownika;
    private boolean czyZatwierdzona;

}
