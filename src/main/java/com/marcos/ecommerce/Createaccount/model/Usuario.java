package com.marcos.ecommerce.createaccount.model;

import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {

    @NotNull(message = "o email é obrigatorio")
    @Email(message= " email invalido")
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message= "O nome não pode estar vazio")
    @Size(min = 3, max = 100, message =  "O nome deve ter entre 3 e 100")
    private String nome;

    @NotNull(message = "Senha é um campo obrigatorio")
    @Size(min = 6, max = 50, message = "A senha deve conter a seguinte range")
    private String password;


    // ========== Ainda não implementado =============

    // Um usuário pode ter muitos produtos:

    // @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    // private List<Produto> produto;

    // um produto pode ter somente um usuario
    // Criação da foreign key "usuario_email" referenciando o email do Usuario
    // @ManyToOne
    // @joinColum(name = "usuario_email", referencedColumnName = "email", nullable = false)
    // private Usuario usuario;

}