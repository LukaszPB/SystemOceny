package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.ValidPodKategoriaExist;
import com.example.projektgruptest.validator.dodawanieOsiagniec.ValidIloscPunktowD;
import com.example.projektgruptest.validator.dodawanieOsiagniec.ValidUserCanAddOsiagniecia;
import com.example.projektgruptest.validator.ocena.ValidPracownikListExist;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidIloscPunktowD
@ValidUserCanAddOsiagniecia
public class DodawanieOsiagniecDTO {
    @ValidPracownikListExist
    private List<Long> pracownikIdList;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 1, max = 250, message = "Name length must be between 1 and 250 characters")
    private String nazwa;
    private int iloscPunktow;
    @ValidPodKategoriaExist
    private String podKategoria;
    @NotNull(message = "Date can't be empty")
    private Date data;
    private boolean zatwierdzone;
}
