package com.marcos.ecommerce.cart.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemCartShoppingResponse {
    private String produtoId;
    private Integer quantidade;
}
