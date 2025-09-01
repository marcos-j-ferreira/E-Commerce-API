package com.marcos.ecommerce.auth.controller;

// definir que é um controller e rotas
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

//métodos
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;

// respostas 
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

// validações e injeções
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

//DTO
import com.marcos.ecommerce.auth.dtos.AuthUserRequestDTO;
import com.marcos.ecommerce.auth.dtos.AuthUserResponseDTO;

import com.marcos.ecommerce.auth.service.AuthoService;

@RestController
@RequestMapping("/api/v1/auth")
public class ControllerAuth {

    private final AuthoService authoService;

   @Autowired
    public ControllerAuth(AuthoService authoService){
        this.authoService = authoService;
    }

    @GetMapping("/")
    public final String test(){
        String teste = "{\"Status\":\"API rodando com sucesso\"}";
        return teste;
    }

    @PostMapping("/loginn")
    public ResponseEntity<AuthUserResponseDTO> loginn(@RequestBody AuthUserRequestDTO loginDTO){
        String token = authoService.login(loginDTO.getEmail(), loginDTO.getPassword());

        for(int i = 0; i < 20; i++){
            System.out.println("Controller - passed: "+ loginDTO);
        }

        return ResponseEntity.ok(new AuthUserResponseDTO(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponseDTO> login(@Valid @RequestBody AuthUserRequestDTO loginDTO){
        String token = authoService.login(loginDTO.getEmail(), loginDTO.getPassword());
        AuthUserResponseDTO response = new AuthUserResponseDTO(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/token")
    public ResponseEntity<String> testToken(){

        String response = "{\"Status\":\"Token Valido\"}";
        return ResponseEntity
                .status(200)
                .header("Content-Type", "application/json")
                .body(response);
                    
    }
} 