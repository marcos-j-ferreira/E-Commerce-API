package com.marcos.ecommerce.createaccount.service;

import com.marcos.ecommerce.createaccount.model.Usuario;
import com.marcos.ecommerce.createaccount.payload.UsuarioRequestDTO;
import com.marcos.ecommerce.createaccount.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String createUser(UsuarioRequestDTO userDTO) {

        // Validação simples dos campos
        if (userDTO.getNome() == null || userDTO.getEmail() == null || userDTO.getPassword() == null ||
            userDTO.getNome().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            return "Erro na criação de usuário: dados inválidos";
        }

        // Criar a entidade Usuario e salvar
        Usuario usuario = new Usuario();
        usuario.setNome(userDTO.getNome());
        usuario.setEmail(userDTO.getEmail());
        usuario.setPassword(userDTO.getPassword());

        usuarioRepository.save(usuario);

        return "Usuário criado com sucesso";
    }
}

// @Service
// public class UsuarioService {

//     @Autowired UsuarioRepository usuarioRepository;

//     public final String createUser( UsuarioRequestDTO userDTO){

//         List<String> data = new ArrayList<>();

//         data.add(userDTO.getNome());
//         data.add(userDTO.getEmail());
//         data.add(userDTO.getPassword());

//         if(!data.isEmpty()){
//             return "Erro na criação de usuario";
//         }else{
//             usuarioRepository.save(data.get(0), data.get(1), data.get(2));
//             return "usuario criado com sucesso;";
//         }

//     }


// }