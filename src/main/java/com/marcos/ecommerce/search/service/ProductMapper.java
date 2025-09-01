package com.marcos.ecommerce.search.service;
import com.marcos.ecommerce.search.dtos.*;

import com.marcos.ecommerce.product.entity.Product;

public class ProductMapper {

    public static ProductSearchRequestDTO toDTO(Product produto) {
        ProductSearchRequestDTO dto = new ProductSearchRequestDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        return dto;
    }
}
