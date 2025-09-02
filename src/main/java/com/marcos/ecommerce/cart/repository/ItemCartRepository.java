package com.marcos.ecommerce.cart.repository;

import com.marcos.ecommerce.cart.entity.CartItem;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemCartRepository extends JpaRepository<CartItem, Long>{    
}
