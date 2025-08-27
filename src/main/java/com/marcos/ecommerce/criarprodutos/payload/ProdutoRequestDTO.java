package com.marcos.ecommerce.criarprodutos.payload;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;

import com.marcos.ecommerce.criarprodutos.enty.Produtos;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoRequestDTO {

    @NotNull(message = "O nome é obrigatorio")
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull(message = " preço obrigatorio")
    private double preco;

    @NotNull(message = " número de estoque obrigatorio")
    private int estoque;

    @NotNull(message = "Descrição obrigatorio")
    @Size(min = 2, max = 200)
    private String descricao;

    @NotNull(message = "o email é obrigatorio")
    @Email(message = "email invalido")
    private String email;
}