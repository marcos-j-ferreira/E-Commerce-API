package com.marcos.ecommerce.product.controller;

import com.marcos.ecommerce.product.dtos.ProductRquestDTO;
import com.marcos.ecommerce.product.entity.Product;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import com.marcos.ecommerce.product.service.ProductService;


@RestController
@RequestMapping("/api/v1/produtos")

public class ControllerProduct{


    @Autowired
    private ProductService serviceProduto;
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
    public final ResponseEntity<?> createProduct(@RequestBody ProductRquestDTO produtoDTO){

        String response = serviceProduto.createProduct(produtoDTO);
        String json = "{\"Status\":\"API rodnado com sucesso\", \"Response\": \""+ response +" \" }";

        return ResponseEntity
                .status(201)
                .header("Content-Type", "application/json")
                .body(json);
    }

    @PostMapping("/update")
    public final ResponseEntity<?> updateProductt(@RequestBody ProductRquestDTO produtoDTO){

        String response = serviceProduto.updatProduct(produtoDTO);

        String json = "{\"Status\":\"Produto atualizado\", \"Response\":\""+ response+" \"}";

        return ResponseEntity
                .status(200)
                .header("Content-Type", "application/json")
                .body(json);
    }

    @DeleteMapping("/delete/{id}")
    public final ResponseEntity<?> deleteProduct(@PathVariable Long id){

        String response = serviceProduto.deleteProduct(id);

        String json = "{\"Message\":\" sucesso\", \"Response\": \" "+response+" \"}";

        return ResponseEntity
                .status(201)
                .header("Content-Type", "application/json")
                .body(json);

    }

}