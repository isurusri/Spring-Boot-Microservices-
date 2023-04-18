package com.example.shopping.controller;

import com.example.shopping.model.Product;
import com.example.shopping.service.ShoppingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(ShoppingController.class)
class ShoppingControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ShoppingService shoppingService;

    @Test
    void findAll() {
        // create some sample products
        Product product1 = new Product("1", "Product 1", 10, 34.5);
        Product product2 = new Product("2", "Product 2", 20, 65.5);
        Product product3 = new Product("3", "Product 3", 30, 43.5);

        // mock the shoppingService.findAll() method to return a Flux of products
        Mockito.when(shoppingService.findAll())
                .thenReturn(Flux.just(product1, product2, product3));

        // send a GET request to the "/shopping" endpoint and expect a JSON array of products
        webTestClient.get().uri("http://localhost:8082/api/v1/shopping")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .hasSize(3)
                .contains(product1, product2, product3);
    }

    @Test
    void getProduct() {
        // create a sample product
        Product product = new Product("1", "Product 1", 10, 34.5);

        // mock the shoppingService.getProductById() method to return a Mono of product
        Mockito.when(shoppingService.getProductById("1"))
                .thenReturn(Mono.just(product));

        // send a GET request to the "shopping/1" endpoint and expect a JSON product
        webTestClient.get().uri("http://localhost:8082/api/v1/shopping/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Product.class)
                .isEqualTo(product);
    }
}