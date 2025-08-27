package com.marcos.ecommerce.criarprodutos.controller;

import com.marcos.ecommerce.criarprodutos.payload.ProdutoRequestDTO;
import com.marcos.ecommerce.criarprodutos.enty.Produtos;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import com.marcos.ecommerce.criarprodutos.service.ServiceProduto;


@RestController
@RequestMapping("/api/v1/produtos")

public class ControllerProduto{


    @Autowired
    private ServiceProduto serviceProduto;
    //private final ProdutoRequestDTO produtoDTO;

    // @Autowired
    // public ControllerProduto (ServiceProduto serviceProduto, ProdutoRequestDTO produtoDTO){
    //     this.serviceProduto = serviceProduto;
    //     //this.produtoDTO = produtoDTO;
    // }

    
    @GetMapping("/")
    public final String test(){
        String teste = "{\"Status\":\"API rodando com sucesso\"}";
        return teste;
    }

    @PostMapping("/criar")
    public final ResponseEntity<?> createProduct(@RequestBody ProdutoRequestDTO produtoDTO){

        String response = serviceProduto.createProduct(produtoDTO);
        String json = "{\"Status\":\"API rodnado com sucesso\", \"Response\": \""+ response +" \" }";

        return ResponseEntity
                .status(201)
                .header("Content-Type", "application/json")
                .body(json);
    }

    @PostMapping("/update")
    public final ResponseEntity<?> updateProductt(@RequestBody ProdutoRequestDTO produtoDTO){

        String response = serviceProduto.updatProduct(produtoDTO);

        String json = "{\"Status\":\"Produto atualizado\", \"Response\":\""+ response+" \"}";

        return ResponseEntity
                .status(200)
                .header("Content-Type", "application/json")
                .body(json);
    }

}