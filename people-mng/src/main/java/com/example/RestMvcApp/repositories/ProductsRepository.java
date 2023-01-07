package com.example.RestMvcApp.repositories;

import com.example.RestMvcApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductByNameContainsIgnoreCase(String name);
}
