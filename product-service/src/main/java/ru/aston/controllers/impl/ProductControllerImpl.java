package ru.aston.controllers.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.controllers.ProductController;
import ru.aston.dto.ProductDTO;
import ru.aston.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    @GetMapping("/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable("productId") int productId) {
        return productService.getProductById(productId);
    }

    @Override
    @PostMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void addProduct(@RequestBody ProductDTO productDTO) {
        productService.addProduct(productDTO);
    }

    @Override
    @PutMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
    }

    @Override
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") int productId) {
        productService.deleteProduct(productId);
    }
}
