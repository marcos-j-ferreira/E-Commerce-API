package com.marcos.ecommerce.creaetaccout.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

//import 
import org.springframework.beans.factory.annotation.Autowired;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //@Autowired
    //ResponseErro responseError(404, );

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseErro> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ResponseErro responseErr = new ResponseErro(400, errors, "/api/v1/creaetaccout/");
        return ResponseEntity.badRequest().header("content-Type", "application/json").body(responseErr);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseErro> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());

        ResponseErro responseErr = new ResponseErro(400, error, "/api/v1/creataccout");

        return ResponseEntity.badRequest().header("Content-Type","application/json").body(responseErr);
    }
}
