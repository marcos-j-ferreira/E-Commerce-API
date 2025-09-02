package com.marcos.ecommerce.product.repository;

import com.marcos.ecommerce.product.entity.Product;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByNome(String nome);
    List<Product> findByPrecoGreaterThan(Double preco);
    List<Product> findByUsuarioId(Long usuarioId);
    Optional<Product> findByIdAndUsuarioId(Long produtoId, Long usuarioId);
    void deleteById(Long id);
}
