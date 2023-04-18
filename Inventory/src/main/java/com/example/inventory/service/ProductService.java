package com.example.inventory.service;

import com.example.inventory.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> findAll();

    Optional<Product> findById(Integer id);

    void createProduct(Product product);

    void updateProduct(Integer id, Product product);

    void deleteById(Integer id);
}
