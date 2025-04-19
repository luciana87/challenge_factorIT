package com.luciana.challenge_factorIT.controllers;


import com.luciana.challenge_factorIT.dtos.requests.CartRequestDTO;
import com.luciana.challenge_factorIT.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luciana.challenge_factorIT.dtos.responses.CartResponseDTO;


@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create")
    @Operation(
            summary = "Crea un carrito de compras personalizado según el rol del usuario.",
            description = "Este endpoint permite crear un tipo de carrito específico según el rol del usuario autenticado. Se espera recibir un objeto de tipo CartRequestDTO con la información necesaria para la creación del mismo."
    )
    public ResponseEntity<CartResponseDTO> createCart (@RequestBody @Valid CartRequestDTO cartRequestDTO) {
        CartResponseDTO response = new CartResponseDTO();
        try {
            response = cartService.createCart(cartRequestDTO);
            return new ResponseEntity<CartResponseDTO>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage("Error al crear el carrito: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
