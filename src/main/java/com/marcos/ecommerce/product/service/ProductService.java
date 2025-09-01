package com.marcos.ecommerce.product.service;


import com.marcos.ecommerce.product.entity.Product;
import com.marcos.ecommerce.product.dtos.ProductRquestDTO;
import com.marcos.ecommerce.product.repository.ProductRepository;
import com.marcos.ecommerce.account.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.marcos.ecommerce.account.entity.User;

@Service
public class ProductService{

    private final ProductRepository produtoRepository;
    private final UserRepository usuarioRepository;
    //private final

    @Autowired
    public ProductService(ProductRepository produtoRepository, UserRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    } 

    public final String createProduct(ProductRquestDTO produtoDTO){

        Long id = usuarioRepository.searchByEmail(produtoDTO.getEmail());
        User usuario = new User();
        usuario.setId(id);

        Product produto = new Product();
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

    public final String updatProduct(ProductRquestDTO produtoDTO){

        Long userID = usuarioRepository.searchByEmail(produtoDTO.getEmail());
        User usuario = new User();
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

    public final String deleteProduct(long id){
        produtoRepository.deleteById(id);
        return "Produto deletado com sucesso";
    }
}