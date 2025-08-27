package com.marcos.ecommerce.criarprodutos.service;


import com.marcos.ecommerce.criarprodutos.enty.Produtos;
import com.marcos.ecommerce.criarprodutos.payload.ProdutoRequestDTO;
import com.marcos.ecommerce.criarprodutos.repository.ProdutoRepository;
import com.marcos.ecommerce.createaccount.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.marcos.ecommerce.createaccount.model.Usuario;

@Service
public class ServiceProduto{

    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    //private final

    @Autowired
    public ServiceProduto(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    } 

    public final String createProduct(ProdutoRequestDTO produtoDTO){

        Long id = usuarioRepository.searchByEmail(produtoDTO.getEmail());
        Usuario usuario = new Usuario();
        usuario.setId(id);

        Produtos produto = new Produtos();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setEstoque(produtoDTO.getEstoque());
        produto.setUsuario(usuario);
        //produto.setEmail(id);

        //produto.setId(id);
        produtoRepository.save(produto);

        return "Produto salvo com sucesso";
    }

    public final String updatProduct(ProdutoRequestDTO produtoDTO){

        Long userID = usuarioRepository.searchByEmail(produtoDTO.getEmail());
        Usuario usuario = new Usuario();
        usuario.setId(userID);

        Long id = userID;

        return produtoRepository.findById(userID)
            .map(produto -> {
                if (produtoDTO.getNome() != null) {
                    produto.setNome(produtoDTO.getNome());
                }
                if (produtoDTO.getDescricao() != null) {
                    produto.setDescricao(produtoDTO.getDescricao());
                }
                if (produtoDTO.getEstoque() == 0) {
                    produto.setEstoque(produtoDTO.getEstoque());
                }
                if (produtoDTO.getPreco() == 0) {
                    produto.setPreco(produtoDTO.getPreco());
                }
                produto.setUsuario(usuario);
                produtoRepository.save(produto);
                return "Informações atualizadas com sucesso";
            })
             .orElse("Erro na atualização: usuario nçao encontrao");
    }
}