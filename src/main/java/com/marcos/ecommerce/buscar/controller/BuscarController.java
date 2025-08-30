package com.marcos.ecommerce.buscar.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.marcos.ecommerce.buscar.serveci.BuscarService;

import com.marcos.ecommerce.criarprodutos.enty.Produtos;

import com.marcos.ecommerce.buscar.payload.ProdutoSearchResponseDTO;

@RestController
@RequestMapping("/api/v1/search")
public class BuscarController{

    @Autowired
    private BuscarService buscarService;

    @GetMapping
    public ResponseEntity<ProdutoSearchResponseDTO> search(
        @RequestParam(required = false) String nome,
        @RequestParam(required = false) String categoria,
        @RequestParam(required = false) Double minPreco,
        @RequestParam(required = false) Double maxPreco,
        Pageable pageable
    ){

        ProdutoSearchResponseDTO pdResponse = buscarService.search(nome, categoria, minPreco, maxPreco, pageable);

        return ResponseEntity
                .status(200)
                .header("Content-Type", "application/json") 
                .body(pdResponse);
    }
}
