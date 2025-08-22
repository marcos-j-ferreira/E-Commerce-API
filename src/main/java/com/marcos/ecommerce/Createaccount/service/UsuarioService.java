package com.marcos.ecommerce.createaccount.service;

import com.marcos.ecommerce.createaccount.model.Usuario;
import com.marcos.ecommerce.createaccount.payload.UsuarioRequestDTO;
import com.marcos.ecommerce.createaccount.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.marcos.ecommerce.createaccount.securityconfig.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    //injeção de depenências via Autowired
    @Autowired 
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(UsuarioRequestDTO userDTO) {

        String getPasswordEncode = passwordEncoder.encode(userDTO.getPassword());
        //System.out.println(getPasswordEncode);

        // Criar a entidade Usuario e salvar
        Usuario usuario = new Usuario();
        usuario.setNome(userDTO.getNome());
        usuario.setEmail(userDTO.getEmail());
        usuario.setPassword(getPasswordEncode);
        //usuario.setPassword(encodePassword.encode(userDTO.getPassword()));


        // String existEmail = usuarioRepository.findUserByEmail(usuario.getEmail());
        // if(existEmail == usuario.getEmail()){
        //     return "email já em uso";
        // }

        boolean existEmail = usuarioRepository.existsByEmail(usuario.getEmail());

        if(existEmail){
            return "O e-mail informado já está em uso";
        }

        System.out.println(existEmail + "  banco : "+ userDTO.getEmail());

        if (userDTO.getNome() == null || userDTO.getEmail() == null || userDTO.getPassword() == null ||
            userDTO.getNome().isEmpty() || userDTO.getEmail().isEmpty() || userDTO.getPassword().isEmpty()) {
            return "Erro na criação de usuário: dados inválidos";
        }

        usuarioRepository.save(usuario);
        return "Usuário criado com sucesso";
    }

    public String updatUser(UsuarioRequestDTO userDTO) {

        Long usuarioId = usuarioRepository.findByEmail(userDTO.getEmail())
                .map(Usuario::getId)
                .orElse(null);  

        String getPasswordEncode = userDTO.getPassword() != null
                ? passwordEncoder.encode(userDTO.getPassword())
                : null;

        return usuarioRepository.findById(usuarioId)
            .map(usuario -> {
                if (userDTO.getNome() != null) {
                    usuario.setNome(userDTO.getNome());
                }
                if (getPasswordEncode != null) {
                    usuario.setPassword(getPasswordEncode);
                }
                if (userDTO.getEmail() != null) {
                    usuario.setEmail(userDTO.getEmail());
                }

                usuarioRepository.save(usuario);
                return "Informações atualizadas com sucesso.";
            })
            .orElse("Erro na atualização: usuário não encontrado.");
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