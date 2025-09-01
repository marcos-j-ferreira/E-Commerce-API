package com.marcos.ecommerce.auth.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AuthUserRequestDTO {

    @NotNull(message = "o email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "O nome não pode estar vazio")
    @Size(min = 6, max = 50, message = "A senha deve ter entre 6 e 50 caracteres")
    private String password;

}