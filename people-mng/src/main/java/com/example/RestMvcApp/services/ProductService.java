package com.example.RestMvcApp.services;

import com.example.RestMvcApp.models.Person;
import com.example.RestMvcApp.models.Product;
import com.example.RestMvcApp.models.ProductStatus;
import com.example.RestMvcApp.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class ProductService {
    private final ProductsRepository productsRepository;

    @Autowired
    public ProductService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    @Transactional
    public void save(Product product) {
        enrichProduct(product);
        productsRepository.save(product);
    }

    @Transactional
    public void update(int id, Product updatedProduct) {
        updatedProduct.setId(id);
        productsRepository.save(updatedProduct);
    }

    public Product findById(int id) {
        return productsRepository.findById(id).orElse(null);
    }

    public List<Product> findByName(String name) {
        return productsRepository.findProductByNameContainsIgnoreCase(name);
    }

    @Transactional
    public void delete(int id) {
        productsRepository.deleteById(id);
    }

    private void enrichProduct(Product product) {
            if (product.getAmount() > 0)
                product.setStatus(ProductStatus.IN_STOCK);
            else
                product.setStatus(ProductStatus.OUT_OF_STOCK);
    }
}
