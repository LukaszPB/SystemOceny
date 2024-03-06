package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.ValidIloscPunktow;
import com.example.projektgruptest.validator.ValidPodKategoriaExist;
import com.example.projektgruptest.validator.ValidUserCanAddToWnisek;
import com.example.projektgruptest.validator.ValidWniosekExist;
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
@ValidIloscPunktow
public class OsiagniecieDTO {
    private Long idOsiagniecia;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 1, max = 250, message = "Name length must be between 1 and 250 characters")
    private String nazwa;
    private int iloscPunktow;
    @NotNull(message = "Date can't be empty")
    private Date data;
    private boolean czyZatwierdzone;
    @ValidPodKategoriaExist
    private String podKategoriaNazwa;
    @ValidWniosekExist
    @ValidUserCanAddToWnisek
    private Long idWniosku;
}
