package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.dtos.requests.CartRequestDTO;
import com.luciana.challenge_factorIT.dtos.responses.CartResponseDTO;
import com.luciana.challenge_factorIT.entities.Cart;
import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.enums.CartType;
import com.luciana.challenge_factorIT.enums.Role;
import com.luciana.challenge_factorIT.repositories.CartRepository;
import com.luciana.challenge_factorIT.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final PromotionalDateService promotionalDateService;


    public CartService(CartRepository cartRepository, UserService userService, PromotionalDateService promotionalDateService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.promotionalDateService = promotionalDateService;
    }

    @Transactional
    public CartResponseDTO createCart(CartRequestDTO cartRequestDTO) throws Exception{
        Optional<UserEntity> user = userService.findById(cartRequestDTO.getUserId());
        if (user.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or doesn't exists");
        }

        Role role = user.get().getRole();
        boolean isPromotionalDate = (promotionalDateService.findByDate(cartRequestDTO.getCreatedAt()));
        CartType type = getCartType(role, isPromotionalDate);

        Cart cart = mapToEntity(cartRequestDTO, user.get(), type);
        cart = cartRepository.save(cart);

        return new CartResponseDTO(cart.getId(), "CREADO", "Carrito creado exitosamente.");
    }

    private Cart mapToEntity(CartRequestDTO cartRequestDTO, UserEntity user, CartType type) {
        LocalDateTime date = Utils.parseToLocalDateTime(cartRequestDTO.getCreatedAt());
        return new Cart(user, date, type);
    }

    private CartType getCartType(Role role, boolean isPromotionalDate) {
        CartType type = CartType.COMMON;
        if (role.equals(Role.VIP)) {
            type = CartType.VIP;
        } else if (isPromotionalDate){
            type = CartType.SPECIAL_DATE;
        }
        return type;
    }

}

