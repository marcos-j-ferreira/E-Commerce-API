package com.marcos.ecommerce.carrinho.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCarrinhoResponse {
    private String produtoId;
    private Integer quantidade;
}
