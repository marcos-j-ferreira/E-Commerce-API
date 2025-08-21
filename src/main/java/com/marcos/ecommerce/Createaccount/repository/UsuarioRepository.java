package com.marcos.ecommerce.createaccount.repository;

import com.marcos.ecommerce.createaccount.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
}


