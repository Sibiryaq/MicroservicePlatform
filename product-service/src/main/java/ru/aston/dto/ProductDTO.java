package ru.aston.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.aston.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int stock;

    public static ProductDTO fromProductDTO(Product product) {
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock()
        );
    }
    public static Product fromProductEntity(ProductDTO productDTO) {
        return new Product(
                productDTO.getProductId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getStock()
        );
    }
}
