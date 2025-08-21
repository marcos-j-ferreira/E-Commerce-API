package com.marcos.ecommerce.createaccount.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

// métodos
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import com.marcos.ecommerce.createaccount.service.UsuarioService;
import com.marcos.ecommerce.createaccount.payload.UsuarioRequestDTO;



@RestController
@RequestMapping("creataccount")
public class ControllerUser {

    @Autowired
    private UsuarioService userService;


    @GetMapping("/run")
    public final String testeRun(){
        String status = "{\"Status\": \" API rodando com sucesso \" }";
        return status;
    }

    @PostMapping("/")
    public final ResponseEntity<String> creatAccount(@RequestBody @Valid UsuarioRequestDTO userDTO){
        String response = userService.createUser(userDTO);

        return ResponseEntity
                .status(200)
                .body(response);
    }

    
}
