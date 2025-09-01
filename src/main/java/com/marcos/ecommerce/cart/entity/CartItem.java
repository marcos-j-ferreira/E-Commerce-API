package com.marcos.ecommerce.cart.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "item_carrinho")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idProduto;
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Cart carrinho;
    
}