package com.marcos.ecommerce.search.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRequestDTO{

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private int estoque;
}