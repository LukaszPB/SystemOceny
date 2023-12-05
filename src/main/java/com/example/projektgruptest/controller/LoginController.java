package com.example.projektgruptest.controller;

import com.example.projektgruptest.config.security.UserWithPracownik;
import com.example.projektgruptest.model.auth.LoginDTO;
import com.example.projektgruptest.model.auth.LoginErrorResponseDTO;
import com.example.projektgruptest.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final LoginService loginService;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword()));
            var user = (UserWithPracownik) authentication.getPrincipal();
            var loginResponse = loginService.createLoginResponse(user);

            return ResponseEntity.ok(loginResponse);

        } catch (BadCredentialsException e) {
            LoginErrorResponseDTO errorResponse = new LoginErrorResponseDTO(HttpStatus.BAD_REQUEST, "Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            LoginErrorResponseDTO errorResponse = new LoginErrorResponseDTO(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
