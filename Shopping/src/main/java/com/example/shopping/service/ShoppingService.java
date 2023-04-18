package com.example.shopping.service;

import com.example.shopping.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ShoppingService {
    Flux<Product> findAll();
    Mono<Product> getProductById(String id);
}
