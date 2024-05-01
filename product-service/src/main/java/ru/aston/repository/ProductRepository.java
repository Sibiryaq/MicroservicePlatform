package ru.aston.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
