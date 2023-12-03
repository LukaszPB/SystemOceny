package com.example.projektgruptest.service;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.auth.LoginResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public LoginResponseDTO createLoginResponse(UserWithPracownik user) {
        var dto = LoginResponseDTO.builder()
                .rola(user.getAuthorities().stream().findFirst().orElseThrow().toString())
                .pracownik(user.getPracownik())
                .build();

        return dto;
    }
}
