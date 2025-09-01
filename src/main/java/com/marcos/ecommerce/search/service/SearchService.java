package com.marcos.ecommerce.search.service;

import jakarta.persistence.criteria.Predicate;   // usado no CriteriaBuilder
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marcos.ecommerce.product.repository.ProductRepository;
import com.marcos.ecommerce.product.entity.Product;

import java.util.ArrayList;
import java.util.List;

import com.marcos.ecommerce.search.dtos.ProductSearchRequestDTO;
import com.marcos.ecommerce.search.dtos.ProductSearchResponseDTO;

@Service
public class SearchService {

    @Autowired
    private ProductRepository produtoRepository;

    public ProductSearchResponseDTO search(String nome, String categoria, Double minPreco, Double maxPreco, Pageable pageable) {

        Page<Product> produtosPage = produtoRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null && !nome.isEmpty()) {
                predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (categoria != null && !categoria.isEmpty()) {
                predicates.add(cb.equal(root.get("categoria"), categoria));
            }

            if (minPreco != null && maxPreco != null) {
                predicates.add(cb.between(root.get("preco"), minPreco, maxPreco));
            } else if (minPreco != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("preco"), minPreco));
            } else if (maxPreco != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("preco"), maxPreco));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        List<ProductSearchRequestDTO> produtosDTO = produtosPage.getContent().stream()
            .map(ProductMapper::toDTO)
            .toList();

        ProductSearchResponseDTO response = new ProductSearchResponseDTO();
        response.setProdutos(produtosDTO);
        response.setPaginaAtual(produtosPage.getNumber());
        response.setTotalPaginas(produtosPage.getTotalPages());
        response.setTotalElementos(produtosPage.getTotalElements());

        response.setNome(nome);
        response.setCategoria(categoria);
        response.setMinPreco(minPreco);
        response.setMaxPreco(maxPreco);

        return response;
    }

    // public Page<Produtos> search(String nome, String categoria, Double minPreco, Double maxPreco, Pageable pageable) {

    //     ResponseDTO response = new ResponseDTO();

    //     return produtoRepository.findAll((root, query, cb) -> {
    //         List<Predicate> predicates = new ArrayList<>();

    //         if (nome != null && !nome.isEmpty()) {
    //             predicates.add(cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));

    //             response.setNome(nome);
    //         }

    //         if (categoria != null && !categoria.isEmpty()) {
    //             predicates.add(cb.equal(root.get("categoria"), categoria));

    //             response.setDescricao(categoria);
    //         }

    //         if (minPreco != null && maxPreco != null) {
    //             predicates.add(cb.between(root.get("preco"), minPreco, maxPreco));

    //         } else if (minPreco != null) {
    //             predicates.add(cb.greaterThanOrEqualTo(root.get("preco"), minPreco));
    //         } else if (maxPreco != null) {
    //             predicates.add(cb.lessThanOrEqualTo(root.get("preco"), maxPreco));
    //         }

    //         return cb.and(predicates.toArray(new Predicate[0]));
    //     }, pageable);
    // }
    
}