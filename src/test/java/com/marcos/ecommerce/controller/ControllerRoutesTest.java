package com.marcos.ecommerce.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcos.ecommerce.account.controller.ControllerAccount;
import com.marcos.ecommerce.account.service.UserService;
import com.marcos.ecommerce.auth.controller.ControllerAuth;
import com.marcos.ecommerce.auth.service.AuthService;
import com.marcos.ecommerce.cart.controller.ControllerCart;
import com.marcos.ecommerce.cart.entity.Cart;
import com.marcos.ecommerce.cart.entity.CartItem;
import com.marcos.ecommerce.cart.service.CartService;
import com.marcos.ecommerce.product.controller.ControllerProduct;
import com.marcos.ecommerce.product.service.ProductService;
import com.marcos.ecommerce.search.dtos.ProductSearchRequestDTO;
import com.marcos.ecommerce.search.dtos.ProductSearchResponseDTO;
import com.marcos.ecommerce.search.controller.SearchController;
import com.marcos.ecommerce.search.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
class ControllerRoutesTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @Mock
    private ProductService productService;

    @Mock
    private CartService cartService;

    @Mock
    private SearchService searchService;

    private MockMvc accountMvc;
    private MockMvc authMvc;
    private MockMvc productMvc;
    private MockMvc cartMvc;
    private MockMvc searchMvc;

    @BeforeEach
    void setUp() {
        ControllerAccount accountController = new ControllerAccount();
        ReflectionTestUtils.setField(accountController, "userService", userService);
        accountMvc = standaloneSetup(accountController).build();

        authMvc = standaloneSetup(new ControllerAuth(authService)).build();

        ControllerProduct productController = new ControllerProduct();
        ReflectionTestUtils.setField(productController, "serviceProduto", productService);
        productMvc = standaloneSetup(productController).build();

        ControllerCart cartController = new ControllerCart();
        ReflectionTestUtils.setField(cartController, "cartService", cartService);
        cartMvc = standaloneSetup(cartController).build();

        SearchController searchController = new SearchController();
        ReflectionTestUtils.setField(searchController, "buscarService", searchService);
        searchMvc = standaloneSetup(searchController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    void shouldCreateUserAccount() throws Exception {
        when(userService.createUser(any())).thenReturn("User created successful");

        accountMvc.perform(post("/api/v1/users/newUsers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new AccountPayload(
                                "Marcos Ferreira",
                                "marcos@email.com",
                                "senha123"
                        ))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("User created successful"));
    }

    @Test
    void shouldDeleteUserById() throws Exception {
        when(userService.deleteUser(10L)).thenReturn("account deleted sucessfully");

        accountMvc.perform(delete("/api/v1/users/delete/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(204));
    }

    @Test
    void shouldLoginAndReturnToken() throws Exception {
        when(authService.login("marcos@email.com", "senha123")).thenReturn("jwt-token");

        authMvc.perform(post("/api/v1/auth/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new LoginPayload(
                                "marcos@email.com",
                                "senha123"
                        ))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token"));
    }

    @Test
    void shouldValidateTokenRoute() throws Exception {
        authMvc.perform(get("/api/v1/auth/token"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Token is Valid")));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        when(productService.createProduct(any())).thenReturn("Product saved with successful!!");

        productMvc.perform(post("/api/v1/product/create")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new ProductPayload(
                                "Teclado",
                                199.90,
                                12,
                                "Teclado mecanico",
                                "seller@email.com"
                        ))))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Product saved with successful!!")));
    }

    @Test
    void shouldDeleteProductById() throws Exception {
        when(productService.deleteProduct(7L)).thenReturn("Product deleted successfyl!!");

        productMvc.perform(delete("/api/v1/product/delete/7"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Product deleted successfyl!!")));
    }

    @Test
    void shouldSearchProducts() throws Exception {
        ProductSearchResponseDTO response = new ProductSearchResponseDTO(
                List.of(new ProductSearchRequestDTO(1L, "Mouse", "Mouse sem fio", 89.90, 5)),
                0,
                1,
                1,
                "Mouse",
                null,
                10.0,
                100.0
        );
        when(searchService.search(eq("Mouse"), eq(null), eq(10.0), eq(100.0), any(Pageable.class)))
                .thenReturn(response);

        searchMvc.perform(get("/api/v1/search")
                        .param("nome", "Mouse")
                        .param("minPreco", "10")
                        .param("maxPreco", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.produtos[0].nome").value("Mouse"))
                .andExpect(jsonPath("$.totalElementos").value(1));
    }

    @Test
    void shouldAddItemToCart() throws Exception {
        Cart cart = cartWithOneItem();
        when(cartService.adicionarItem(anyString(), anyString(), anyInt())).thenReturn(cart);

        cartMvc.perform(post("/api/v1/cartShopping/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(new AddCartPayload(
                                "user@email.com",
                                "1",
                                2
                        ))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value("user@email.com"))
                .andExpect(jsonPath("$.itens[0].produtoId").value("1"))
                .andExpect(jsonPath("$.itens[0].quantidade").value(2));
    }

    @Test
    void shouldGetCartByUserId() throws Exception {
        when(cartService.searchCartShooping("user@email.com")).thenReturn(cartWithOneItem());

        cartMvc.perform(get("/api/v1/cartShopping/user@email.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCarrinho").value(99))
                .andExpect(jsonPath("$.itens[0].produtoId").value("1"));
    }

    private Cart cartWithOneItem() {
        Cart cart = new Cart();
        cart.setId(99L);
        cart.setIdUser("user@email.com");

        CartItem item = new CartItem();
        item.setIdProduto("1");
        item.setQuantidade(2);
        item.setCarrinho(cart);

        cart.setItens(List.of(item));
        return cart;
    }

    private record AccountPayload(String nome, String email, String password) {
    }

    private record LoginPayload(String email, String password) {
    }

    private record ProductPayload(String nome, double preco, int estoque, String descricao, String email) {
    }

    private record AddCartPayload(String userId, String produtoId, Integer quantidade) {
    }
}
