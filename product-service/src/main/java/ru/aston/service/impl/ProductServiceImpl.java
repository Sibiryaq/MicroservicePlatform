package ru.aston.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aston.dto.ProductDTO;
import ru.aston.entity.Product;
import ru.aston.repository.ProductRepository;
import ru.aston.service.ProductService;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDTO getProductById(int productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        return ProductDTO.fromProductDTO(product);
    }


    @Override
    public void addProduct(ProductDTO productDTO) {
        productRepository.save(ProductDTO.fromProductEntity(productDTO));
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        productRepository.save(ProductDTO.fromProductEntity(productDTO));
    }

    @Override
    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }
}
