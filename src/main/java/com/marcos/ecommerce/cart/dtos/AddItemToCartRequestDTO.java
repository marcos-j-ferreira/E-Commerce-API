package com.marcos.ecommerce.cart.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddItemToCartRequestDTO {
    private String userId;      
    private String produtoId;   
    private Integer quantidade; 
}
