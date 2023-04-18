package com.example.shopping.controller;

import com.example.shopping.model.Product;
import com.example.shopping.service.ShoppingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/shopping")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public Flux<Product> findAll() {
        return shoppingService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProduct(@PathVariable String id) {
        return shoppingService.getProductById(id);
    }
}
