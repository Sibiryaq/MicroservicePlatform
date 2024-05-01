package ru.aston.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import ru.aston.dto.ProductDTO;


public interface ProductController {
    @GetMapping
    ProductDTO getProductById(int productId);

    @PostMapping
    void addProduct(ProductDTO productDTO);

    @PutMapping
    void updateProduct(ProductDTO productDTO);

    @DeleteMapping
    void deleteProduct(int productId);
}
