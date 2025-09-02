package com.marcos.ecommerce.account.exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

import org.springframework.dao.DataIntegrityViolationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String path = "/api/v1/users/createAccount/1.0.6";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErro> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ResponseErro responseErr = new ResponseErro(400, errors, path);
        return ResponseEntity.badRequest().header("content-Type", "application/json").body(responseErr);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseErro> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());

        ResponseErro responseErr = new ResponseErro(400, error, path);

        return ResponseEntity.badRequest().header("Content-Type","application/json").body(responseErr);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseErro> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        ResponseErro responseErr = new ResponseErro(404, Map.of("error", ex.getMessage()), request.getRequestURI());
        return ResponseEntity.status(404).body(responseErr);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseErro> handleDataIntegrity(DataIntegrityViolationException ex, HttpServletRequest request) {
        ResponseErro responseErr = new ResponseErro(409, Map.of("error", "Violação de integridade: " + ex.getMostSpecificCause().getMessage()), request.getRequestURI());
        return ResponseEntity.status(409).body(responseErr);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseErro> handleGeneric(Exception ex, HttpServletRequest request) {
        ResponseErro responseErr = new ResponseErro(500, Map.of("error", "Erro interno no servidor"), request.getRequestURI());
        return ResponseEntity.status(500).body(responseErr);
    }

}
