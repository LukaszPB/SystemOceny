package com.example.projektgruptest.model.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
public class LoginErrorResponseDTO {
    private HttpStatus httpStatus;
    private String message;
}
