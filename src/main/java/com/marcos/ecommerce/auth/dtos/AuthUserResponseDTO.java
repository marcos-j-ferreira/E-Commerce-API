package com.marcos.ecommerce.auth.dtos;

import lombok.*;
import jakarta.validation.constraints.*;

@Data 
@AllArgsConstructor
@NoArgsConstructor

public class AuthUserResponseDTO {

    private String token;
    public String getToken(){
        return token;
    }
}