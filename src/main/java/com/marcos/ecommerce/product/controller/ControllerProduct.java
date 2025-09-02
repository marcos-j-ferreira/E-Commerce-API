package com.marcos.ecommerce.product.controller;

import com.marcos.ecommerce.product.service.ProductService;
import com.marcos.ecommerce.product.dtos.ProductRquestDTO;
import com.marcos.ecommerce.product.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/v1/product")

public class ControllerProduct{

    @Autowired
    private ProductService serviceProduto;

    @GetMapping("/")
    public final String test(){
        String statusResponse = "{\"Status\":\"Api running successfully\"}";
        return statusResponse;
    }

    @PostMapping("/create")
    public final ResponseEntity<?> createProduct(@RequestBody ProductRquestDTO dataForCreateNewProductDTO){
        String messageResponseReturn = serviceProduto.createProduct(dataForCreateNewProductDTO);
        String jsonResponseReturn = "{\"Status\":\"Api running successfully\", \"Response\": \""+ messageResponseReturn +" \" }";
        return ResponseEntity
                .status(201)
                .header("Content-Type", "application/json")
                .body(jsonResponseReturn);
    }

    @PostMapping("/update")
    public final ResponseEntity<?> updateProductt(@RequestBody ProductRquestDTO dataForUpdateProductDTO){
        String messageResponseReturn = serviceProduto.updatProduct(dataForUpdateProductDTO);
        String jsonResponseReturn = "{\"Status\":\"Product update\", \"Response\":\""+ messageResponseReturn+" \"}";
        return ResponseEntity
                .status(200)
                .header("Content-Type", "application/json")
                .body(jsonResponseReturn);
    }

    @DeleteMapping("/delete/{id}")
    public final ResponseEntity<?> deleteProduct(@PathVariable Long idProduct){
        String messageResponseReturn = serviceProduto.deleteProduct(idProduct);
        String jsonResponseReturn = "{\"Message\":\" successfully\", \"Response\": \" "+messageResponseReturn+" \"}";
        return ResponseEntity
                .status(201)
                .header("Content-Type", "application/json")
                .body(jsonResponseReturn);
    }
}
