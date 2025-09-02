package com.marcos.ecommerce.cart.dtos;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartShoppingResponseDTO {
    private Long idCarrinho;
    private String userId;
    private List<ItemCartShoppingResponse> itens;
}