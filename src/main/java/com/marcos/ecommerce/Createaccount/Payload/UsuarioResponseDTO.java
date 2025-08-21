package com.marcos.ecommerce.creataccount.payload;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private int status;
    private String message; 
    private String path;
}