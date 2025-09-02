package com.marcos.ecommerce.account.service;

import com.marcos.ecommerce.account.dtos.CreateUserRequest;
import com.marcos.ecommerce.account.repository.UserRepository;
import com.marcos.ecommerce.account.entity.User;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired 
    public UserService(UserRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String createUser(CreateUserRequest dataForCreatingAnAccountUserRequestDTO) {
        User newUser = new User();
        String getPasswordEncode = passwordEncoder.encode(dataForCreatingAnAccountUserRequestDTO.getPassword());

        newUser.setNome(dataForCreatingAnAccountUserRequestDTO.getNome());
        newUser.setEmail(dataForCreatingAnAccountUserRequestDTO.getEmail());
        newUser.setPassword(getPasswordEncode);

        boolean existEmailOfUser = usuarioRepository.existsByEmail(newUser.getEmail());
        if(existEmailOfUser){
            return "The email provided is already in use";
        }

        if (dataForCreatingAnAccountUserRequestDTO.getNome() == null || dataForCreatingAnAccountUserRequestDTO.getEmail() == null || dataForCreatingAnAccountUserRequestDTO.getPassword() == null ||
            dataForCreatingAnAccountUserRequestDTO.getNome().isEmpty() || dataForCreatingAnAccountUserRequestDTO.getEmail().isEmpty() || dataForCreatingAnAccountUserRequestDTO.getPassword().isEmpty()) {
            return "Error creating user: invalid data";
        }
        usuarioRepository.save(newUser);
        return "User created sucessfuly";
    }

    public String updatUser(CreateUserRequest dataForUpdateUserRequestDTO) {
        Long userId = usuarioRepository.findByEmail(dataForUpdateUserRequestDTO.getEmail())
                .map(User::getId)
                .orElse(null);  

        String getPasswordEncode = dataForUpdateUserRequestDTO.getPassword() != null
                ? passwordEncoder.encode(dataForUpdateUserRequestDTO.getPassword())
                : null;

        return usuarioRepository.findById(userId)
            .map(user -> {
                if (dataForUpdateUserRequestDTO.getNome() != null) {
                    dataForUpdateUserRequestDTO.setNome(dataForUpdateUserRequestDTO.getNome());
                }
                if (getPasswordEncode != null) {
                    user.setPassword(getPasswordEncode);
                }
                if (dataForUpdateUserRequestDTO.getEmail() != null) {
                    dataForUpdateUserRequestDTO.setEmail(dataForUpdateUserRequestDTO.getEmail());
                }

                usuarioRepository.save(user);
                return "Information updated sucessfully";
            })
            .orElse("Update error: user not found");
    }
    
    public final String deleteUser(Long userIdByPathVariable){
        if(usuarioRepository.existsById(userIdByPathVariable)){
            usuarioRepository.deleteById(userIdByPathVariable);
            return "Account deleted sucessfully";
        }
        return "Error deleting account";
    }
}
