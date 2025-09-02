package com.marcos.ecommerce.account.repository;

import com.marcos.ecommerce.account.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String emailOfUser);
    boolean existsByEmail(String emailOfUser);

    @Transactional
    boolean existsById(Long idOfuser);
    void deleteById(Long idOfuser);

    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Long searchByEmail(@Param("email") String emailOfUser);
}
