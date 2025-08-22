package com.marcos.ecommerce.createaccount.repository;

import com.marcos.ecommerce.createaccount.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    //uma possibilidade de fazer busca personalizadas no jpa
    // @Query("SELECT u.email FROM Usuario u WHERE u.email = :email")
    // String findUserByEmail(@Param("email") String email);

    //mais comum e mais usada
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    //Optional<Usuario> findByEmail(String email);
    
}


