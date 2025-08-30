package com.marcos.ecommerce.buscar.serveci;
import com.marcos.ecommerce.buscar.payload.*;

import com.marcos.ecommerce.criarprodutos.enty.Produtos;

public class ProdutoMapper {

    public static ProdutoDTO toDTO(Produtos produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        return dto;
    }
}
