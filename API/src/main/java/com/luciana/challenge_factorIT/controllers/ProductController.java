package com.luciana.challenge_factorIT.controllers;

import com.luciana.challenge_factorIT.dtos.responses.ProductResponseDTO;
import com.luciana.challenge_factorIT.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> retriveAllProducts () {
        try {
            return new ResponseEntity<List<ProductResponseDTO>>(productService.retrivevAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
