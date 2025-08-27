package com.marcos.ecommerce.createaccount.repository;

import com.marcos.ecommerce.createaccount.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    //uma possibilidade de fazer busca personalizadas no jpa
    // @Query("SELECT u.email FROM Usuario u WHERE u.email = :email")
    // String findUserByEmail(@Param("email") String email);

    //mais comum e mais usada

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

    @Transactional
    boolean existsById(Long id);
    void deleteById(Long id);

    //Optional<Usuario> findByEmail(String email);

    //Optional<Usuario> deleteByEmail(String email);


    //@Transactional


    // @Query("DELETE FROM User u WHERE u.email = :email")
    // void deleteByEmailCustom(@Param("email") String email);

    @Query("SELECT u.id FROM Usuario u WHERE u.email = :email")
    Long searchByEmail(@Param("email") String email);
    
}


