package com.marcos.ecommerce.createaccount.payload;

import jakarta.validation.constraints.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "o nome é obrigatorio")
    @Size(min= 3, max = 100, message = " o compode de ter entre 3 a 100 caracteres")
    private String nome;

    @NotBlank(message = "o email é obrigatorio")
    @Email(message= " E-mail invalido")
    private String email;

    @NotBlank(message = "A senha é obrigatoria")
    @Size(min =6, message = "A senha deve ter pelo menos 6 caracteres")
    private String password;
}