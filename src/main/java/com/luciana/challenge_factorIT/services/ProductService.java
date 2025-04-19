package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.entities.Product;
import com.luciana.challenge_factorIT.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found or doesn't exists");
        }
        return optionalProduct.get();
    }
}
