package com.example.projektgruptest.service;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.auth.LoginResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public LoginResponseDTO createLoginResponse(UserWithPracownik user, String sessionId) {
        var dto = LoginResponseDTO.builder()
                .rola(user.getAuthorities().stream().findFirst().orElseThrow().toString())
                .pracownik(user.getPracownik())
                .JSessionId(sessionId)
                .build();

        return dto;
    }
}
