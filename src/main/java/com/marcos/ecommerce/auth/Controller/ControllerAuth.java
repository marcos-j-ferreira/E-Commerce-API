package com.marcos.ecommerce.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import com.marcos.ecommerce.auth.dtos.AuthUserRequestDTO;
import com.marcos.ecommerce.auth.dtos.AuthUserResponseDTO;
import com.marcos.ecommerce.auth.service. AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class ControllerAuth {

    private final  AuthService authService;

   @Autowired
    public ControllerAuth( AuthService authService){
        this.authService = authService;
    }

    @GetMapping("/")
    public final String routeForTestRunningAPIWithSimpleMessage(){
        String ResponseForTestRunOfAPI = "{\"Status\":\"Api running successfully\", \"Test\":\"My applications is runing with devoolss\"}";
        return ResponseForTestRunOfAPI;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponseDTO> routeForLoginOfUser(@Valid @RequestBody AuthUserRequestDTO dataRequestOfuserDTO){
        String token = authService.login(dataRequestOfuserDTO.getEmail(), dataRequestOfuserDTO.getPassword());
        AuthUserResponseDTO resonseWithTokenForUser = new AuthUserResponseDTO(token);
        return ResponseEntity.ok(resonseWithTokenForUser);
    }

    @GetMapping("/token")
    public ResponseEntity<String> routeForTestIfTokenIsvalid(){

        String responseIfTokenIsValid = "{\"Status\":\"Token is Valid\"}";
        return ResponseEntity
                .status(200)
                .header("Content-Type", "application/json")
                .body(responseIfTokenIsValid);
    }
} 
