package com.marcos.ecommerce.cart.service;

//import com.marcos.ecommerce.cart.dtos.CarrinhoResponse;
//import com.marcos.ecommerce.cart.dtos.ItemCarrinhoResponse;
//import com.marcos.ecommerce.cart.dtos.AddItemToCartRequestDTo;

import com.marcos.ecommerce.cart.repository.CartRepository;
import com.marcos.ecommerce.cart.entity.CartItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.ecommerce.cart.entity.Cart;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository carrinhoRepository;

    public Cart adicionarItem(String userId, String idProduto, int quantidade) {
        Cart carrinho = carrinhoRepository.findByIdUser(userId)
                .orElseGet(() -> new Cart(null, userId, new ArrayList<>()));

        CartItem item = new CartItem();
        item.setIdProduto(idProduto);
        item.setQuantidade(quantidade);
        item.setCarrinho(carrinho);

        carrinho.getItens().add(item);

        return carrinhoRepository.save(carrinho);
    }

    public Cart buscarCarrinho(String userId) {
        return carrinhoRepository.findByIdUser(userId)
                .orElseThrow(() -> new RuntimeException("Cart is not found"));
    }

}
