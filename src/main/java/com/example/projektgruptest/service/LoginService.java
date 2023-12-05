package com.example.projektgruptest.service;

import com.example.projektgruptest.auth.JwtUtil;
import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.auth.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtUtil jwtUtil;

    public LoginResponseDTO createLoginResponse(UserWithPracownik user) {
        var dto = LoginResponseDTO.builder()
                .token(jwtUtil.createToken(user))
                .rola(user.getAuthorities().stream().findFirst().orElseThrow().toString())
                .pracownik(user.getPracownik())
                .build();

        return dto;
    }
}
