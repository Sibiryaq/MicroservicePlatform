package ru.aston.service;

import ru.aston.dto.ProductDTO;

import java.util.List;

public interface ProductService {


    ProductDTO getProductById(int productId);


    void addProduct(ProductDTO productDTO);

    void updateProduct(ProductDTO productDTO);

    void deleteProduct(int productId);
}
