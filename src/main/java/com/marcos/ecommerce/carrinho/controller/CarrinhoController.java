package com.marcos.ecommerce.carrinho.controller;

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
import com.marcos.ecommerce.carrinho.enty.Carrinho;


import com.marcos.ecommerce.carrinho.payload.AdicionarItemCarrinhoRequest;
import com.marcos.ecommerce.carrinho.payload.CarrinhoResponse;
import com.marcos.ecommerce.carrinho.payload.ItemCarrinhoResponse;

import com.marcos.ecommerce.carrinho.service.CarrinhoService;

@RestController
@RequestMapping("/api/v1/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/adicionar")
    public ResponseEntity<CarrinhoResponse> adicionarItem(@RequestBody AdicionarItemCarrinhoRequest request) {
        Carrinho carrinho = carrinhoService.adicionarItem(
                request.getUserId(),
                request.getProdutoId(),
                request.getQuantidade()
        );


        CarrinhoResponse response = new CarrinhoResponse();

        response.setIdCarrinho(carrinho.getId());
        response.setUserId(carrinho.getIdUser());
        response.setItens(
            carrinho.getItens().stream()
                .map(i -> new ItemCarrinhoResponse(i.getIdProduto(), i.getQuantidade()))
                .toList() // se estiver em Java 11, use Collectors.toList()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CarrinhoResponse> buscarCarrinho(@PathVariable String userId) {
        Carrinho carrinho = carrinhoService.buscarCarrinho(userId);

        CarrinhoResponse response = new CarrinhoResponse(
                carrinho.getId(),
                carrinho.getIdUser(),
                carrinho.getItens().stream()
                        .map(i -> new ItemCarrinhoResponse(i.getIdProduto(), i.getQuantidade()))
                        .toList()
        );

        return ResponseEntity.ok(response);
    }
}
