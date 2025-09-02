package com.marcos.ecommerce.account.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserRequest {
    
        @NotNull(message = "email is required")
        @Email(message = "Invalid email")
        String email;

        @NotBlank(message = "The name can't be empty")
        @Size(min = 3, max = 100, message = "The name must be between 3 and 100 characters long")
        String nome;

        @NotNull(message = "Password is required")
        @Size(min = 6, max = 50, message = "The passowrd must be between 6 and 50 characters long")
        String password;
}