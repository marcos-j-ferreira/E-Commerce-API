package com.marcos.ecommerce.login.payload;

import lombok.*;
import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.*;

@Data //getter and setter
@AllArgsConstructor
@NoArgsConstructor

public class LoginResponseDTO {

    private String token;
    
    public String getToken(){
        return token;
    }
}