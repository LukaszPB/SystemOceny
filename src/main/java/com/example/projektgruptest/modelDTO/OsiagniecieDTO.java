package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.ValidIloscPunktow;
import com.example.projektgruptest.validator.ValidPodKategoriaExist;
import com.example.projektgruptest.validator.ValidWniosekExist;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidIloscPunktow
public class OsiagniecieDTO {
    private long idOsiagniecia;

    @NotEmpty(message = "Name can't be empty")
    private String nazwa;
    private int iloscPunktow;

    @NotNull(message = "Date can't be empty")
    private Date data;

    private boolean czyZatwierdzone;

    @NotEmpty(message = "Subcategory can't be empty")
    @ValidPodKategoriaExist
    private String podKategoriaNazwa;
    @ValidWniosekExist
    private long idWniosku;
}
