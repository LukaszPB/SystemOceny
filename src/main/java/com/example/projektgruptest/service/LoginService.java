package com.example.projektgruptest.service;

import com.example.projektgruptest.auth.JwtUtil;
import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.modelDTO.PracownikDTO;
import com.example.projektgruptest.model.auth.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtUtil jwtUtil;
    private final PracownikService pracownikService;

    public LoginResponseDTO createLoginResponse(UserWithPracownik user) {
        PracownikDTO pracownikDto = null;
        boolean czyMaPodwladnych = false;

        if(user.getPracownik() != null) {
            pracownikDto = PracownikDTO.builder()
                    .idPracownika(user.getPracownik().getIdPracownika())
                    .imie(user.getPracownik().getImie())
                    .nazwisko(user.getPracownik().getNazwisko())
                    .emailSluzbowy(user.getPracownik().getEmailSluzbowy())
                    .grupa(user.getPracownik().getGrupa().getNazwa())
                    .stanowiskoNazwa(user.getPracownik().getPracownikStanowisko().getNazwa())
                    .stopienNaukowyNazwa(user.getPracownik().getStopienNaukowy().getNazwa())
                    .build();
            czyMaPodwladnych = pracownikService.getPracownicyPrzelozonego(pracownikDto.getIdPracownika()).size()>0;
        }

        var dto = LoginResponseDTO.builder()
                .token(jwtUtil.createToken(user))
                .rola(user.getAuthorities().stream().findFirst().orElseThrow().toString())
                .pracownik(pracownikDto)
                .czyMaPodwladnych(czyMaPodwladnych)
                .build();

        return dto;
    }
}
