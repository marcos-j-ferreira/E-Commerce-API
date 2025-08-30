package com.marcos.ecommerce.buscar.payload;

import lombok.*;
import java.util.List;

//import com.marcos.ecommerce.buscar.payload.Pr 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoSearchResponseDTO {

    private List<ProdutoDTO> produtos;
    private int paginaAtual;
    private int totalPaginas;
    private long totalElementos;
    
    private String nome;
    private String categoria;
    private Double minPreco;
    private Double maxPreco;

}
