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
                    .id(user.getPracownik().getId())
                    .imie(user.getPracownik().getImie())
                    .nazwisko(user.getPracownik().getNazwisko())
                    .grupa(user.getPracownik().getGrupa().getNazwa())
                    .stanowisko(user.getPracownik().getPracownikStanowisko().getNazwa())
                    .stopienNaukowy(user.getPracownik().getStopienNaukowy().getNazwa())
                    .katedra(user.getPracownik().getWydzialKatedra().getNazwaKatedry())
                    .czyMoznaOcenic(user.getPracownik().getCzyMoznaOcenic())
                    .build();
            czyMaPodwladnych = false;
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
