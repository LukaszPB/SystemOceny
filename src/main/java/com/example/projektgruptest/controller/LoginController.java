package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.auth.LoginDTO;
import com.example.projektgruptest.model.auth.LoginResponseDTO;
import com.example.projektgruptest.service.LoginService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginDTO,
                                 HttpServletRequest request) {
        try {
            request.login(loginDTO.getLogin(), loginDTO.getPassword());
        } catch (ServletException e) {
            SecurityContextHolder.getContext().setAuthentication(null);
            throw new AuthenticationCredentialsNotFoundException("Invalid username or password");
        }

        var auth = (Authentication) request.getUserPrincipal();
        var user = (UserWithPracownik) auth.getPrincipal();
        var sessionId = request.getSession().getId();

        return loginService.createLoginResponse(user, sessionId);
    }
}
