package com.example.projektgruptest.service;

import com.example.projektgruptest.auth.JwtUtil;
import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.PracownikDTO;
import com.example.projektgruptest.model.auth.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtUtil jwtUtil;

    public LoginResponseDTO createLoginResponse(UserWithPracownik user) {
        var pracownikDto = PracownikDTO.builder()
                .idPracownika(user.getPracownik().getIdPracownika())
                .imie(user.getPracownik().getImie())
                .nazwisko(user.getPracownik().getNazwisko())
                .emailSluzbowy(user.getPracownik().getEmailSluzbowy())
                .build();

        var dto = LoginResponseDTO.builder()
                .token(jwtUtil.createToken(user))
                .rola(user.getAuthorities().stream().findFirst().orElseThrow().toString())
                .pracownik(pracownikDto)
                .build();

        return dto;
    }
}
