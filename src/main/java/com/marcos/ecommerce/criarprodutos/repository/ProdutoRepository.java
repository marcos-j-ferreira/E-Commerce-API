package com.marcos.ecommerce.criarprodutos.repository;

import com.marcos.ecommerce.criarprodutos.enty.Produtos;
import com.marcos.ecommerce.createaccount.model.Usuario;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ProdutoRepository extends JpaRepository<Produtos, Long>, JpaSpecificationExecutor<Produtos> {

    List<Produtos> findByNome(String nome);
    List<Produtos> findByPrecoGreaterThan(Double preco);
    List<Produtos> findByUsuarioId(Long usuarioId);
    
    Optional<Produtos> findByIdAndUsuarioId(Long produtoId, Long usuarioId);

    void deleteById(Long id);

    //OptionalupdateProduto;



    //Optional<Usuario> findByEmail(String email); 

    // @Query("SELECT u.id FROM Usuario u WHERE u.email = :email")
    // Long searchByEmail(@Param("email") String email);

}



// INSERT INTO produtos (id, nome, preco, estoque, descricao, usuario_id)
// VALUES (1, 'notebook', 100, 10, 'bom', 
//         (SELECT id FROM usuarios WHERE email = 'celta@gmail.com'));