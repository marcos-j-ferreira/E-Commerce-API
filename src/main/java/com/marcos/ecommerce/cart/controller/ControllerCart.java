package com.marcos.ecommerce.cart.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import com.marcos.ecommerce.cart.entity.Cart;


import com.marcos.ecommerce.cart.dtos.AddItemToCartRequestDTO;
import com.marcos.ecommerce.cart.dtos.CartShoppingResponseDTO;
import com.marcos.ecommerce.cart.dtos.ItemCartShoppingResponse;

import com.marcos.ecommerce.cart.service.CartService;

@RestController
@RequestMapping("/api/v1/carrinho")
public class ControllerCart {

    @Autowired
    private CartService carrinhoService;

    @PostMapping("/adicionar")
    public ResponseEntity<CartShoppingResponseDTO> adicionarItem(@RequestBody AddItemToCartRequestDTO request) {
        Cart carrinho = carrinhoService.adicionarItem(
                request.getUserId(),
                request.getProdutoId(),
                request.getQuantidade()
        );


        CartShoppingResponseDTO response = new CartShoppingResponseDTO();

        response.setIdCarrinho(carrinho.getId());
        response.setUserId(carrinho.getIdUser());
        response.setItens(
            carrinho.getItens().stream()
                .map(i -> new ItemCartShoppingResponse(i.getIdProduto(), i.getQuantidade()))
                .toList() // se estiver em Java 11, use Collectors.toList()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartShoppingResponseDTO> buscarCarrinho(@PathVariable String userId) {
        Cart carrinho = carrinhoService.buscarCarrinho(userId);

        CartShoppingResponseDTO response = new CartShoppingResponseDTO(
                carrinho.getId(),
                carrinho.getIdUser(),
                carrinho.getItens().stream()
                        .map(i -> new ItemCartShoppingResponse(i.getIdProduto(), i.getQuantidade()))
                        .toList()
        );

        return ResponseEntity.ok(response);
    }
}
