package com.marcos.ecommerce.auth.dtos;

import lombok.*;
import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.*;

@Data //getter and setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthUserResponseDTO {

    private String token;
    
    public String getToken(){
        return token;
    }
}