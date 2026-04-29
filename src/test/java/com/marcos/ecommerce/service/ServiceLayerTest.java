package com.marcos.ecommerce.service;

import com.marcos.ecommerce.account.dtos.CreateUserRequest;
import com.marcos.ecommerce.account.entity.User;
import com.marcos.ecommerce.account.repository.UserRepository;
import com.marcos.ecommerce.account.service.UserService;
import com.marcos.ecommerce.auth.security.JwtUtil;
import com.marcos.ecommerce.auth.service.AuthService;
import com.marcos.ecommerce.cart.entity.Cart;
import com.marcos.ecommerce.cart.repository.CartRepository;
import com.marcos.ecommerce.cart.service.CartService;
import com.marcos.ecommerce.product.dtos.ProductRquestDTO;
import com.marcos.ecommerce.product.entity.Product;
import com.marcos.ecommerce.product.repository.ProductRepository;
import com.marcos.ecommerce.product.service.ProductService;
import com.marcos.ecommerce.search.dtos.ProductSearchResponseDTO;
import com.marcos.ecommerce.search.service.SearchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceLayerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @Test
    void userServiceShouldCreateUserWithEncodedPassword() {
        UserService service = new UserService(userRepository, passwordEncoder);
        CreateUserRequest request = new CreateUserRequest("marcos@email.com", "Marcos", "senha123");
        when(passwordEncoder.encode("senha123")).thenReturn("encoded-password");
        when(userRepository.existsByEmail("marcos@email.com")).thenReturn(false);

        String result = service.createUser(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertThat(result).isEqualTo("User created sucessfuly");
        assertThat(userCaptor.getValue().getEmail()).isEqualTo("marcos@email.com");
        assertThat(userCaptor.getValue().getPassword()).isEqualTo("encoded-password");
    }

    @Test
    void userServiceShouldNotCreateDuplicatedEmail() {
        UserService service = new UserService(userRepository, passwordEncoder);
        CreateUserRequest request = new CreateUserRequest("marcos@email.com", "Marcos", "senha123");
        when(passwordEncoder.encode("senha123")).thenReturn("encoded-password");
        when(userRepository.existsByEmail("marcos@email.com")).thenReturn(true);

        String result = service.createUser(request);

        assertThat(result).isEqualTo("The email provided is already in use");
        verify(userRepository, never()).save(any());
    }

    @Test
    void authServiceShouldReturnJwtWhenCredentialsAreValid() {
        AuthService service = new AuthService(userRepository, passwordEncoder, jwtUtil);
        User user = new User();
        user.setEmail("marcos@email.com");
        user.setPassword("encoded-password");
        when(userRepository.findByEmail("marcos@email.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("senha123", "encoded-password")).thenReturn(true);
        when(jwtUtil.generateToken("marcos@email.com")).thenReturn("jwt-token");

        String token = service.login("marcos@email.com", "senha123");

        assertThat(token).isEqualTo("jwt-token");
    }

    @Test
    void authServiceShouldRejectInvalidPassword() {
        AuthService service = new AuthService(userRepository, passwordEncoder, jwtUtil);
        User user = new User();
        user.setEmail("marcos@email.com");
        user.setPassword("encoded-password");
        when(userRepository.findByEmail("marcos@email.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("errada", "encoded-password")).thenReturn(false);

        assertThatThrownBy(() -> service.login("marcos@email.com", "errada"))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("invalid password");
    }

    @Test
    void productServiceShouldCreateProductForExistingUserEmail() {
        ProductService service = new ProductService(productRepository, userRepository);
        ProductRquestDTO request = new ProductRquestDTO("Mouse", 89.90, 5, "Mouse sem fio", "seller@email.com");
        when(userRepository.searchByEmail("seller@email.com")).thenReturn(42L);

        String result = service.createProduct(request);

        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(productCaptor.capture());
        assertThat(result).isEqualTo("Product saved with successful!!");
        assertThat(productCaptor.getValue().getNome()).isEqualTo("Mouse");
        assertThat(productCaptor.getValue().getUsuario().getId()).isEqualTo(42L);
    }

    @Test
    void productServiceShouldDeleteProductById() {
        ProductService service = new ProductService(productRepository, userRepository);

        String result = service.deleteProduct(7L);

        verify(productRepository).deleteById(7L);
        assertThat(result).isEqualTo("Product deleted successfyl!!");
    }

    @Test
    void cartServiceShouldCreateCartWhenUserDoesNotHaveOne() {
        CartService service = new CartService();
        org.springframework.test.util.ReflectionTestUtils.setField(service, "carrinhoRepository", cartRepository);
        when(cartRepository.findByIdUser("user@email.com")).thenReturn(Optional.empty());
        when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Cart cart = service.adicionarItem("user@email.com", "1", 2);

        assertThat(cart.getIdUser()).isEqualTo("user@email.com");
        assertThat(cart.getItens()).hasSize(1);
        assertThat(cart.getItens().get(0).getIdProduto()).isEqualTo("1");
        assertThat(cart.getItens().get(0).getQuantidade()).isEqualTo(2);
        verify(cartRepository).save(cart);
    }

    @Test
    void cartServiceShouldReturnExistingCart() {
        CartService service = new CartService();
        org.springframework.test.util.ReflectionTestUtils.setField(service, "carrinhoRepository", cartRepository);
        Cart existingCart = new Cart(1L, "user@email.com", List.of());
        when(cartRepository.findByIdUser("user@email.com")).thenReturn(Optional.of(existingCart));

        Cart cart = service.searchCartShooping("user@email.com");

        assertThat(cart).isSameAs(existingCart);
    }

    @Test
    void searchServiceShouldReturnMappedProductsAndPagination() {
        SearchService service = new SearchService();
        org.springframework.test.util.ReflectionTestUtils.setField(service, "produtoRepository", productRepository);
        Product product = new Product();
        product.setId(1L);
        product.setNome("Mouse");
        product.setDescricao("Mouse sem fio");
        product.setPreco(89.90);
        product.setEstoque(5);
        Pageable pageable = PageRequest.of(0, 10);
        when(productRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(new PageImpl<>(List.of(product), pageable, 1));

        ProductSearchResponseDTO response = service.search("Mouse", null, 10.0, 100.0, pageable);

        assertThat(response.getProdutos()).hasSize(1);
        assertThat(response.getProdutos().get(0).getNome()).isEqualTo("Mouse");
        assertThat(response.getPaginaAtual()).isZero();
        assertThat(response.getTotalElementos()).isEqualTo(1);
        assertThat(response.getMinPreco()).isEqualTo(10.0);
        assertThat(response.getMaxPreco()).isEqualTo(100.0);
    }
}
