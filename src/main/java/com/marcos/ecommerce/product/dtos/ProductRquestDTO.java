package com.marcos.ecommerce.product.dtos;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductRquestDTO {

    @NotNull(message = "name is required")
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull(message = " price is required")
    private double preco;

    @NotNull(message = " number of stock required")
    private int estoque;

    @NotNull(message = "Descrobe is required")
    @Size(min = 2, max = 200)
    private String descricao;

    @NotNull(message = "email is required")
    @Email(message = "Invalid email")
    private String email;
}
