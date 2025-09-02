package com.marcos.ecommerce.search.dtos;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchResponseDTO {

    private List<ProductSearchRequestDTO> produtos;
    private int paginaAtual;
    private int totalPaginas;
    private long totalElementos;
    
    private String nome;
    private String categoria;
    private Double minPreco;
    private Double maxPreco;

}
