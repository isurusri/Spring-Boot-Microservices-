package com.example.inventory.service.implementation;

import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepo;
import com.example.inventory.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;

    public ProductServiceImpl(ProductRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return repo.findById(id);
    }

    @Override
    public void createProduct(Product product) {
        repo.save(product);
    }

    @Override
    public void updateProduct(Integer id, Product product) {
        if (Boolean.FALSE.equals(repo.existsById(id))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found!!");
        }
        repo.save(product);
    }


    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }
}
