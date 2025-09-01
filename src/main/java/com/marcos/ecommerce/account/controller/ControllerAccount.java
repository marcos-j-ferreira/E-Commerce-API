package com.marcos.ecommerce.account.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

// métodos
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import com.marcos.ecommerce.account.service.UserService;
import com.marcos.ecommerce.account.dtos.CreateUserRequest;

import com.marcos.ecommerce.account.dtos.UserSummaryResponse;

@RestController
@RequestMapping("/api/v1/creataccount")
public class ControllerAccount {

    @Autowired
    private UserService userService;

    // @Autowired
    // private UsuarioResponseDTO usuarioResponse;

    @GetMapping("/run")
    public final String testeRun(){
        String status = "{\"Status\": \" API rodando com sucesso \" }";
        return status;
    }

    @PostMapping("/new")
    public final ResponseEntity<UserSummaryResponse> creatAccount(@RequestBody @Valid CreateUserRequest userDTO){

        String response = userService.createUser(userDTO);
        int status = 409;
        if(response.equals("Usuário criado com sucesso")){
            status = 200;
        }

        UserSummaryResponse responseDTO = new UserSummaryResponse(status, response, "/api/v1/createaccout/");

        return ResponseEntity
                .status(status)
                .header("Content-Type","application/json")
                .body(responseDTO);
    }

    @PostMapping("/update")
    public final ResponseEntity<UserSummaryResponse> updateUser(@RequestBody CreateUserRequest userDto){

        String response = userService.updatUser(userDto);

        UserSummaryResponse responseDTO = new UserSummaryResponse(201, response, "/api/v1/createaccount");

        return ResponseEntity
                .status(201)
                .header("Content-Type", "application/json")
                .body(responseDTO);
    }

    @DeleteMapping("/delete/{id}")
    public final ResponseEntity<UserSummaryResponse> deleteUser(@PathVariable Long id){
        String response = userService.deleteUser(id);

        int status = 404;

        if(response.equals("Conta deletado com sucesso")){
            status = 204;    
        }

        UserSummaryResponse responseDTO = new UserSummaryResponse(status, response, "/api/v1/creataccount");


        return ResponseEntity
                .status(200)
                .header("Content-Type", "application/json")
                .body(responseDTO);
    }    
}

