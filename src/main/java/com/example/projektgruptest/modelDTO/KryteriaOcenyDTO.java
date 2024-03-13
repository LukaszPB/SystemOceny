package com.example.projektgruptest.modelDTO;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KryteriaOcenyDTO {
    private long IdKryterium;
    private boolean czyPosiadaStopienNaukowy;
    private boolean czyNaStanowiskuKierowniczym;
    private int progPozytywnejOcenyNB;
    private int progOcenyZWyroznieniemNB;
    private int progPozytywnejOcenyDO;
    private int progOcenyZWyroznieniemDO;
    private String grupa;
}
