package com.example.inventory.controller;

import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;


@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void findAll() throws Exception {
        // create some sample products
        Product product1 = new Product("1", "Product 1", 10, 34.5);
        Product product2 = new Product("2", "Product 2", 20, 65.5);
        Product product3 = new Product("3", "Product 3", 30, 43.5);

        // mock the productService.findAll() method to return a list of products
        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        Mockito.when(productService.findAll())
                .thenReturn(productList);

        // send a GET request to the "/products" endpoint and expect a JSON array of products
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantity").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(34.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantity").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(65.5))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("Product 3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].quantity").value(30))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].price").value(43.5));
    }

    @Test
    void findById() throws Exception {
        // create a sample product
        Product product = new Product("1", "Product 1", 10, 34.5);
        // mock the productService.findById() method to return an Optional of the product
        Mockito.when(productService.findById(1))
                .thenReturn(Optional.of(product));

        // send a GET request to the "/products/1" endpoint and expect a JSON product
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8081/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(34.5));
    }

    @Test
    void createProduct() throws Exception {
        // create a sample product
        Product product = new Product("1", "Product 1", 10, 34.5);
        // mock the productService.createProduct() method to return the product
        Mockito.doNothing().when(productService).createProduct(any(Product.class));
        // send a POST request to the "/products" endpoint with the product JSON data
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8081/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"name\":\"Product 1\",\"quantity\":10,\"price\":34.5}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // verify that the productService.createProduct() method was called with the correct product object
        Mockito.verify(productService, Mockito.times(1)).createProduct(Mockito.eq(product));
    }

    @Test
    void updateProduct() throws Exception {
        // create a sample product
        Product product = new Product("1", "Product 1", 10, 34.5);
        // mock the productService.updateProduct() method
        Mockito.doNothing().when(productService).updateProduct(anyInt(), any(Product.class));

        // send a PUT request to the "/products/1" endpoint with the updated product JSON data
        mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8081/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"name\":\"Product 1 Updated\",\"quantity\":15,\"price\":24.5}"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // verify that the productService.updateProduct() method was called with the correct product ID and object
        Mockito.verify(productService, Mockito.times(1)).updateProduct(Mockito.eq(1), Mockito.eq(new Product("1", "Product 1 Updated", 15, 24.5)));
    }

    @Test
    void deleteById() throws Exception{
        // mock the productService.deleteById() method
        Mockito.doNothing().when(productService).deleteById(anyInt());

        // send a DELETE request to the "/products/1" endpoint
        mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8081/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        // verify that the productService.deleteById() method was called with the correct product ID
        Mockito.verify(productService, Mockito.times(1)).deleteById(Mockito.eq(1));
    }
}