package com.luciana.challenge_factorIT.controllers;


import com.luciana.challenge_factorIT.dtos.requests.CartRequestDTO;
import com.luciana.challenge_factorIT.dtos.requests.ItemRequestDTO;
import com.luciana.challenge_factorIT.dtos.requests.ModifyItemRequestDTO;
import com.luciana.challenge_factorIT.dtos.responses.CartDetailResponseDTO;
import com.luciana.challenge_factorIT.services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.luciana.challenge_factorIT.dtos.responses.CartResponseDTO;
import org.springframework.web.server.ResponseStatusException;


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
            response = cartService.createCart(cartRequestDTO.getUserId(), cartRequestDTO.getCreatedAt());
            return new ResponseEntity<CartResponseDTO>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage("Error al crear el carrito: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{cartId}/item")
    @Operation(
            summary = "Agrega un item al carrito de compras especificado.",
            description = "Este endpoint permite agregar un item al carrito de compras identificado por el ID proporcionado. " +
                    "Se espera recibir un objeto de tipo `ItemRequestDTO` con la información necesaria para su creación. " +
                    "El carrito será actualizado con el nuevo item y se devolverá una respuesta de éxito si la operación fue realizada correctamente."
    )
    public ResponseEntity<String> addItemToCart (@PathVariable Long cartId, @RequestBody @Valid ItemRequestDTO itemRequestDTO) {
        try {
            cartService.addItemToCart(cartId, itemRequestDTO);
            return new ResponseEntity<String>("Se agregó un item al carrito", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al agregar un item al carrito.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cartId}/total")
    public ResponseEntity<Double> getTotalToCart (@PathVariable Long cartId) {
        try {
            double total = cartService.getCartTotal(cartId);
            return new ResponseEntity<Double>(total, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cartId}/item/{itemId}")
    @Operation(
            summary = "Elimina un item del carrito de compras según un ID.",
            description = "Este endpoint permite eliminar un item del carrito de compras identificado por un ID proporcionado. " +
                    "El item será eliminado y se devolverá una respuesta de éxito si la operación fue realizada correctamente."
    )
    public ResponseEntity<String> deleteItemFromCart (@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartService.deleteItemFromCart(cartId, itemId);
            return new ResponseEntity<String>("Item eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al eliminar un item del carrito.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{cartId}/item/{itemId}")
    @Operation(
            summary = "Modifica la cantidad de un producto en un carrito de compras.",
            description = "Este endpoint permite actualizar la cantidad de un producto específico dentro de un carrito de compras determinado " +
                    "Se debe proporcionar el ID del carrito, el ID del producto y la nueva cantidad deseada. Si el producto no existe en el carrito, se devolverá un error."
    )
    public ResponseEntity<String> modifyItemFromCart (@PathVariable @Valid Long cartId, @PathVariable @Valid Long itemId, @RequestBody @Valid ModifyItemRequestDTO requestDTO) {
        try {
            cartService.modifyItemFromCart(cartId, itemId, requestDTO);
            return new ResponseEntity<String>("Item modificado correctamente", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al modificar un item del carrito.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{cartId}/checkout")
    @Operation(
            summary = "Finaliza la compra de un carrito de compras.",
            description = "Este endpoint permite finalizar la compra de un carrito específico, cambiando su estado a 'confirmado'. " +
                    "Una vez confirmado, el carrito no podrá ser modificado. Se espera el ID del carrito como parámetro."
    )
    public ResponseEntity<String> checkoutCart (@PathVariable @Valid Long cartId) {
        try {
            cartService.checkoutCart(cartId);
            return new ResponseEntity<String>("Compra finalizada correctamente", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<String>("Error al finalizar una compra.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cartId}")
    @Operation(
            summary = "Realiza un borrado lógico de un carrito de compras.",
            description = "Este endpoint permite eliminar un carrito de compras de forma lógica, " +
                    "cambiando su estado a 'eliminado'. El carrito no será eliminado físicamente de la base de datos."
    )
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        try {
            cartService.deleteCart(cartId);
            return new ResponseEntity<>("Carrito eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el carrito.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cartId}")
    @Operation(
            summary = "Retorna el detalle del carrito de compras.",
            description = "Este endpoint devuelve el estado actual del carrito, incluyendo el total a pagar, " +
                    "si está confirmado o no, y los productos y sus cantidades dentro del mismo."
    )
    public ResponseEntity<CartDetailResponseDTO> getCart(@PathVariable Long cartId) {
        try {
            CartDetailResponseDTO cartDetail = cartService.getDetailCart(cartId);
            return new ResponseEntity<>(cartDetail, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
