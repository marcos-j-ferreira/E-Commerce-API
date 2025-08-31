package com.marcos.ecommerce.carrinho.repository;

import com.marcos.ecommerce.carrinho.enty.Carrinho;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{


    Optional<Carrinho> findByIdUser(String userId);


    
}