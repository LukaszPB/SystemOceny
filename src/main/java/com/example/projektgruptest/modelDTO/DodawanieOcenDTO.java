package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.EditValidationGrup;
import com.example.projektgruptest.validator.ocena.ValidDatesOrder;
import com.example.projektgruptest.validator.ocena.ValidPracownikListExist;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidDatesOrder
public class DodawanieOcenDTO {
    @ValidPracownikListExist
    private List<PracownikDTO> pracownikDTOList;
    @NotNull(groups = EditValidationGrup.class)
    private Date dataPoczatkowa;
    @NotNull(groups = EditValidationGrup.class)
    private Date dataKoncowa;
}
