package com.example.shopping.service.implementation;

import com.example.shopping.model.Product;
import com.example.shopping.service.ShoppingService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class ShoppingServiceImpl implements ShoppingService {

    private final WebClient webClient;

    public ShoppingServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<Product> findAll() {
        return webClient.get()
                .uri("http://localhost:8081/api/v1/products")
                .retrieve()
                .bodyToFlux(Product.class);
    }

    public Mono<Product> getProductById(String id) {
        return webClient.get()
                .uri("http://localhost:8081/api/v1/products/{id}", id)
                .retrieve()
                .bodyToMono(Product.class);
    }
}
