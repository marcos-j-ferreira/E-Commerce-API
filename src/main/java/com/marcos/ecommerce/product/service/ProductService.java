package com.marcos.ecommerce.product.service;

import com.marcos.ecommerce.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.marcos.ecommerce.account.repository.UserRepository;
import com.marcos.ecommerce.product.dtos.ProductRquestDTO;
import com.marcos.ecommerce.product.entity.Product;
import com.marcos.ecommerce.account.entity.User;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService{

    private final ProductRepository produtoRepository;
    private final UserRepository usuarioRepository;

    @Autowired
    public ProductService(ProductRepository produtoRepository, UserRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    } 

    public final String createProduct(ProductRquestDTO productDataForCreateNewDTO){
        Long id = usuarioRepository.searchByEmail(productDataForCreateNewDTO.getEmail());
        User dataUser = new User();
        dataUser.setId(id);

        Product product = new Product();
        product.setNome(productDataForCreateNewDTO.getNome());
        product.setPreco(productDataForCreateNewDTO.getPreco());
        product.setDescricao(productDataForCreateNewDTO.getDescricao());
        product.setEstoque(productDataForCreateNewDTO.getEstoque());
        product.setUsuario(dataUser);
        produtoRepository.save(product);
        return "Product saved with successful!!";
    }

    public final String updatProduct(ProductRquestDTO productDataForUpdateDTO){
        Long userID = usuarioRepository.searchByEmail(productDataForUpdateDTO.getEmail());
        User user = new User();
        user.setId(userID);

        return produtoRepository.findById(userID)
            .map(produto -> {
                if (productDataForUpdateDTO.getNome() != null) {
                    produto.setNome(productDataForUpdateDTO.getNome());
                }
                if (productDataForUpdateDTO.getDescricao() != null) {
                    produto.setDescricao(productDataForUpdateDTO.getDescricao());
                }
                if (productDataForUpdateDTO.getEstoque() == 0) {
                    produto.setEstoque(productDataForUpdateDTO.getEstoque());
                }
                if (productDataForUpdateDTO.getPreco() == 0) {
                    produto.setPreco(productDataForUpdateDTO.getPreco());
                }
                produto.setUsuario(user);
                produtoRepository.save(produto);
                return "Information updated successful!!";
            })
             .orElse("Update erro: user not found");
    }
    
    public final String deleteProduct(long idProduct){
        produtoRepository.deleteById(idProduct);
        return "Product deleted successfyl!!";
    }
}
