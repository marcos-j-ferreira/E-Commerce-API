package com.marcos.ecommerce.criarprodutos.enty;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;

import com.marcos.ecommerce.createaccount.model.Usuario;

@Entity
@Table(name= "produtos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length  = 20)
    private String nome;

    @Column (nullable = false, length = 5)
    private double preco;

    @Column (nullable = false, length = 5)
    private int estoque;

    @Column (nullable = false, length = 100)
    private String descricao;

    @ManyToOne
    @JoinColumn (name = "email", nullable = false)
    private Usuario usuario;

}



