package com.marcos.ecommerce.carrinho.service;

import com.marcos.ecommerce.carrinho.payload.CarrinhoResponse;
import com.marcos.ecommerce.carrinho.payload.ItemCarrinhoResponse;
import com.marcos.ecommerce.carrinho.payload.AdicionarItemCarrinhoRequest;

import com.marcos.ecommerce.carrinho.repository.CarrinhoRepository;
import com.marcos.ecommerce.carrinho.enty.ItemCarrinho;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.ecommerce.carrinho.enty.Carrinho;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public Carrinho adicionarItem(String userId, String idProduto, int quantidade) {
        Carrinho carrinho = carrinhoRepository.findByIdUser(userId)
                .orElseGet(() -> new Carrinho(null, userId, new ArrayList<>()));

        ItemCarrinho item = new ItemCarrinho();
        item.setIdProduto(idProduto);
        item.setQuantidade(quantidade);
        item.setCarrinho(carrinho);

        carrinho.getItens().add(item);

        return carrinhoRepository.save(carrinho);
    }

    public Carrinho buscarCarrinho(String userId) {
        return carrinhoRepository.findByIdUser(userId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));
    }

}
