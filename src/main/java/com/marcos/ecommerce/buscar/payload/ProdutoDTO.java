package com.marcos.ecommerce.buscar.payload;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO{

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private int estoque;
}