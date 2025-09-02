package com.marcos.ecommerce.search.service;

import com.marcos.ecommerce.product.entity.Product;
import com.marcos.ecommerce.search.dtos.*;

public class ProductMapper {

    public static ProductSearchRequestDTO toDTO(Product product) {
        ProductSearchRequestDTO dto = new ProductSearchRequestDTO();
        dto.setId(product.getId());
        dto.setNome(product.getNome());
        dto.setDescricao(product.getDescricao());
        dto.setPreco(product.getPreco());
        return dto;
    }
}
