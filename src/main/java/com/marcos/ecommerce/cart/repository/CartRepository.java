package com.marcos.ecommerce.cart.repository;

import com.marcos.ecommerce.cart.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{


    Optional<Cart> findByIdUser(String userId);


    
}