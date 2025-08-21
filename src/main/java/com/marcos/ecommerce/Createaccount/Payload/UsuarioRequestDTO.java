package com.marcos.ecommerce.createaccount.payload;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioRequestDTO {
    
        @NotNull(message = "O email é obrigatório")
        @Email(message = "Email inválido")
        String email;

        @NotBlank(message = "O nome não pode estar vazio")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome;

        @NotNull(message = "Senha é obrigatória")
        @Size(min = 6, max = 50, message = "A senha deve ter entre 6 e 50 caracteres")
        String password;
}