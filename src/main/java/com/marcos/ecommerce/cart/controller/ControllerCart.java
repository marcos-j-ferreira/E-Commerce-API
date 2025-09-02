package com.marcos.ecommerce.cart.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import com.marcos.ecommerce.cart.dtos.ItemCartShoppingResponse;
import com.marcos.ecommerce.cart.dtos.AddItemToCartRequestDTO;
import com.marcos.ecommerce.cart.dtos.CartShoppingResponseDTO;
import com.marcos.ecommerce.cart.service.CartService;
import com.marcos.ecommerce.cart.entity.Cart;

@RestController
@RequestMapping("/api/v1/cartShopping")
public class ControllerCart {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartShoppingResponseDTO> addeditemInCartShopping(@RequestBody AddItemToCartRequestDTO dataOfProductForAddInCartShopping) {
        Cart cart = cartService.adicionarItem(
                dataOfProductForAddInCartShopping.getUserId(),
                dataOfProductForAddInCartShopping.getProdutoId(),
                dataOfProductForAddInCartShopping.getQuantidade()
        );
        CartShoppingResponseDTO responseCartShopping = new CartShoppingResponseDTO();
        responseCartShopping.setIdCarrinho(cart.getId());
        responseCartShopping.setUserId(cart.getIdUser());
        responseCartShopping.setItens(
            cart.getItens().stream()
                .map(i -> new ItemCartShoppingResponse(i.getIdProduto(), i.getQuantidade()))
                .toList()
        );
        return ResponseEntity.ok(responseCartShopping);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartShoppingResponseDTO> searchCartShopping(@PathVariable String userId) {
        Cart cartShopping = cartService.searchCartShooping(userId);
    
        CartShoppingResponseDTO responseNewCartShopping = new CartShoppingResponseDTO(
                cartShopping.getId(),
                cartShopping.getIdUser(),
                cartShopping.getItens().stream()
                        .map(i -> new ItemCartShoppingResponse(i.getIdProduto(), i.getQuantidade()))
                        .toList()
        );
        return ResponseEntity.ok(responseNewCartShopping);
    }
}
