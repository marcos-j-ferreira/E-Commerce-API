package com.marcos.ecommerce.login.service;


// injeção
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.marcos.ecommerce.createaccount.repository.UsuarioRepository;
import com.marcos.ecommerce.login.security.JwtUtil;

import java.util.Optional;

import com.marcos.ecommerce.createaccount.model.Usuario;

@Service
public class AuthoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthoService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }
    
    public String login(String email, String senha){
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        
        if(usuarioOptional.isEmpty()){
            throw new RuntimeException("Usuário não encontrado");
        }
        
        Usuario usuario = usuarioOptional.get();
        
        if(!passwordEncoder.matches(senha, usuario.getPassword())){
            throw new RuntimeException("Senha inválida");
        }
        
        return jwtUtil.generateToken(usuario.getEmail());
    }

    public String loginn(String email, String senha){

        for(int i = 0 ; i < 10; i++){
            System.out.println("service - passed: "+ email +" Senha: " +senha);
        }

        // var usuario = usuarioRepository.findByEmail(email)
        //         .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);

        if(usuarioOptional.isEmpty()){
            return "Usuário não encontrado";
        }

        Usuario usuario = usuarioOptional.get();
        
        if(!passwordEncoder.matches(senha, usuario.getPassword())){
            return "Senha inválida"; // throw new RuntimeException
        }

        return jwtUtil.generateToken(usuario.getEmail());
    }


}
