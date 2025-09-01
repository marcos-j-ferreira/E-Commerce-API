package com.marcos.ecommerce.product.repository;

import com.marcos.ecommerce.product.entity.Product;
import com.marcos.ecommerce.account.entity.User;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findByNome(String nome);
    List<Product> findByPrecoGreaterThan(Double preco);
    List<Product> findByUsuarioId(Long usuarioId);
    
    Optional<Product> findByIdAndUsuarioId(Long produtoId, Long usuarioId);

    void deleteById(Long id);

    //OptionalupdateProduto;



    //Optional<Usuario> findByEmail(String email); 

    // @Query("SELECT u.id FROM Usuario u WHERE u.email = :email")
    // Long searchByEmail(@Param("email") String email);

}



// INSERT INTO produtos (id, nome, preco, estoque, descricao, usuario_id)
// VALUES (1, 'notebook', 100, 10, 'bom', 
//         (SELECT id FROM usuarios WHERE email = 'celta@gmail.com'));