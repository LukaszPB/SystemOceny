package com.example.projektgruptest.modelDTO;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OcenaDTO {


    private long idOceny;

    private String nazwa;

    private int iloscPunktow;

    private Date data;
    private long idWniosku;

}
