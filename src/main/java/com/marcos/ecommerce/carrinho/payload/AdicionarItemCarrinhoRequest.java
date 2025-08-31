package com.marcos.ecommerce.carrinho.payload;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdicionarItemCarrinhoRequest {
    private String userId;      
    private String produtoId;   
    private Integer quantidade; 
}
