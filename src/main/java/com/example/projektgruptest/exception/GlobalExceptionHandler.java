package com.example.projektgruptest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }
    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(PermissionDeniedException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(bodyOfResponse);
    }
    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ValidationFailedException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }
    @ExceptionHandler(FileException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(FileException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }
}
