package com.example.projektgruptest.modelDTO;

import com.example.projektgruptest.validator.ValidPodKategoriaExist;
import com.example.projektgruptest.validator.ValidPracownikExist;
import com.example.projektgruptest.validator.osiagniecie.ValidIloscPunktow;
import com.example.projektgruptest.validator.osiagniecie.ValidUserCanAddOsiagniecie;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ValidIloscPunktow
@ValidUserCanAddOsiagniecie
public class OsiagniecieDTO {
    private Long id;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 1, max = 250, message = "Name length must be between 1 and 250 characters")
    private String nazwa;
    private int iloscPunktow;
    @NotNull(message = "Date can't be empty")
    private Date data;
    private boolean zatwierdzone;
    private boolean zarchiwizowana;
    @ValidPodKategoriaExist
    private String podKategoriaNazwa;
    @ValidPracownikExist
    private Long idPracownika;
    private Long idOceny;
    private List<byte[]> pliki;
    private Set<HistoriaModyfikacjiOsiagnieciaDTO> listaModyfikacjiOsiagniec;
}
