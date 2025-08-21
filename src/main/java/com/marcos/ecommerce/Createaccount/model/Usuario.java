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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 255)
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