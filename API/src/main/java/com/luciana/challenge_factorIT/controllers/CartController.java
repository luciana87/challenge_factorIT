package com.luciana.challenge_factorIT.controllers;


import com.luciana.challenge_factorIT.dtos.requests.CartRequestDTO;
import com.luciana.challenge_factorIT.dtos.requests.ItemRequestDTO;
import com.luciana.challenge_factorIT.dtos.requests.ModifyItemRequestDTO;
import com.luciana.challenge_factorIT.dtos.responses.CartDetailResponseDTO;
import com.luciana.challenge_factorIT.dtos.responses.ItemResponseDTO;
import com.luciana.challenge_factorIT.entities.Item;
import com.luciana.challenge_factorIT.services.CartService;
import com.luciana.challenge_factorIT.services.UserAuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import com.luciana.challenge_factorIT.dtos.responses.CartResponseDTO;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping(value = "/cart")
public class CartController {
    private final CartService cartService;
    private final UserAuthService userAuthService;

    public CartController(CartService cartService, UserAuthService userAuthService) {
        this.cartService = cartService;
        this.userAuthService = userAuthService;
    }

    @PostMapping("/create")
    @Operation(
            summary = "Crea un carrito de compras personalizado según el rol del usuario.",
            description = "Este endpoint permite crear un tipo de carrito específico según el rol del usuario autenticado. Se espera recibir un objeto de tipo CartRequestDTO con la información necesaria para la creación del mismo."
    )
    public ResponseEntity<CartResponseDTO> createCart (@RequestBody @Valid CartRequestDTO cartRequestDTO) {
        CartResponseDTO response = new CartResponseDTO();
        try {
            Long userId = userAuthService.getRequestUserId();
            response = cartService.createCart(userId, cartRequestDTO.getCreatedAt());
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
    public ResponseEntity<CartResponseDTO> addItemToCart (@PathVariable Long cartId, @RequestBody @Valid ItemRequestDTO itemRequestDTO) {
        CartResponseDTO responseDTO = new CartResponseDTO();
        responseDTO.setCartId(cartId);
        try {
            Long userId = userAuthService.getRequestUserId();
            cartService.addItemToCart(cartId, itemRequestDTO, userId);
            responseDTO.setMessage("Se agregó un item al carrito");
            return new ResponseEntity<CartResponseDTO>(responseDTO, HttpStatus.CREATED);
        } catch (AccessDeniedException e) {
            responseDTO.setMessage("Error al agregar un item al carrito. El usuario no tiene permisos para acceder o modificar este recurso.");
            return new ResponseEntity<CartResponseDTO>(responseDTO, HttpStatus.FORBIDDEN);
        } catch (ResponseStatusException e) {
            responseDTO.setMessage(e.getReason());
            return new ResponseEntity<CartResponseDTO>(responseDTO, e.getStatusCode());
        } catch (Exception e) {
            responseDTO.setMessage("Error al agregar un item al carrito." + e.getMessage());
            return new ResponseEntity<CartResponseDTO>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cartId}/total")
    public ResponseEntity<Double> getTotalToCart (@PathVariable Long cartId) {
        try {
            Long userId = userAuthService.getRequestUserId();
            double total = cartService.getCartTotal(cartId, userId);
            return new ResponseEntity<Double>(total, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        }  catch (AccessDeniedException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
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
            Long userId = userAuthService.getRequestUserId();
            cartService.deleteItemFromCart(cartId, itemId, userId);
            return new ResponseEntity<String>("Item eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (AccessDeniedException e) {
            return new ResponseEntity<String>("Error al eliminar un item al carrito. El usuario no tiene permisos para acceder o modificar este recurso.", HttpStatus.FORBIDDEN);
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
            Long userId = userAuthService.getRequestUserId();
            cartService.modifyItemFromCart(cartId, itemId, requestDTO, userId);
            return new ResponseEntity<String>("Item modificado correctamente", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        } catch (AccessDeniedException e) {
            return new ResponseEntity<String>("Error al modificar un item del carrito. El usuario no tiene permisos para acceder o modificar este recurso.", HttpStatus.FORBIDDEN);
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
            Long userId = userAuthService.getRequestUserId();
            cartService.checkoutCart(cartId, userId);
            return new ResponseEntity<String>("Compra finalizada correctamente", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            String msj = "No se pudo confirmar la compra: " + e.getReason();
            return new ResponseEntity<>(msj, e.getStatusCode());
        } catch (AccessDeniedException e) {
            return new ResponseEntity<String>("Error al finalizar una compra. El usuario no tiene permisos para acceder o modificar este recurso.", HttpStatus.FORBIDDEN);
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
            Long userId = userAuthService.getRequestUserId();
            cartService.deleteCart(cartId, userId);
            return new ResponseEntity<>("Carrito eliminado correctamente", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            String msj = "No se pudo eliminar el carrito: " + e.getReason();
            return new ResponseEntity<>(msj, e.getStatusCode());
        } catch (AccessDeniedException e) {
            return new ResponseEntity<String>("Error al eliminar el carrito. El usuario no tiene permisos para acceder o modificar este recurso.", HttpStatus.FORBIDDEN);
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
            Long userId = userAuthService.getRequestUserId();
            CartDetailResponseDTO cartDetail = cartService.getDetailCart(cartId, userId);
            return new ResponseEntity<>(cartDetail, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        } catch (AccessDeniedException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
