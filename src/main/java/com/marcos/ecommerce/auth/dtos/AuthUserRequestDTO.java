package com.marcos.ecommerce.auth.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AuthUserRequestDTO {

    @NotNull(message = "Email is required")
    @Email(message = "invalid email")
    private String email;

    @NotNull(message = "The name can't be empty")
    @Size(min = 6, max = 50, message = "The password must be between 6 and 50 characters long")
    private String password;
}
