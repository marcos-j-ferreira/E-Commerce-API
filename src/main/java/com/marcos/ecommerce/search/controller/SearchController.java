package com.marcos.ecommerce.search.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;

import com.marcos.ecommerce.search.dtos.ProductSearchResponseDTO;
import com.marcos.ecommerce.search.service.SearchService;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController{

    @Autowired
    private SearchService buscarService;

    @GetMapping
    public ResponseEntity<ProductSearchResponseDTO> search(
    
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) Double minPreco,
        @RequestParam(required = false) Double maxPreco,
        Pageable pageable
    ){
        ProductSearchResponseDTO pdResponse = buscarService.search(nome, categoria, minPreco, maxPreco, pageable);
        return ResponseEntity
                .status(200)
                .header("Content-Type", "application/json") 
                .body(pdResponse);
    }
}
