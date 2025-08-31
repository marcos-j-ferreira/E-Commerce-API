package com.marcos.ecommerce.carrinho.payload;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoResponse {
    private Long idCarrinho;
    private String userId;
    private List<ItemCarrinhoResponse> itens;
}