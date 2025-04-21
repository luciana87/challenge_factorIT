package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.dtos.responses.ProductResponseDTO;
import com.luciana.challenge_factorIT.entities.Product;
import com.luciana.challenge_factorIT.repositories.ProductRepository;
import com.luciana.challenge_factorIT.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ProductResponseDTO> retrivevAll() {
        List<Product> productos = productRepository.findAllByDeleted(false);
        return productos.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());
    }

    private ProductResponseDTO mapToDTO(Product product) {
        ProductResponseDTO productResponseDTO = Utils.getModelMapper().map(product, ProductResponseDTO.class);

        return productResponseDTO;
    }
}
