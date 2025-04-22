package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.dtos.requests.ItemRequestDTO;
import com.luciana.challenge_factorIT.dtos.requests.ModifyItemRequestDTO;
import com.luciana.challenge_factorIT.dtos.responses.CartResponseDTO;
import com.luciana.challenge_factorIT.dtos.responses.CartDetailResponseDTO;
import com.luciana.challenge_factorIT.dtos.responses.ItemResponseDTO;
import com.luciana.challenge_factorIT.entities.Cart;
import com.luciana.challenge_factorIT.entities.Item;
import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.enums.CartType;
import com.luciana.challenge_factorIT.enums.Role;
import com.luciana.challenge_factorIT.repositories.CartRepository;
import com.luciana.challenge_factorIT.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final PromotionalDateService promotionalDateService;
    private final ItemService itemService;


    public CartService(CartRepository cartRepository, UserService userService, PromotionalDateService promotionalDateService, ItemService itemService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.promotionalDateService = promotionalDateService;
        this.itemService = itemService;
    }

    @Transactional
    public CartResponseDTO createCart(Long userId, String createdAt) throws Exception{
        Optional<UserEntity> optionalUser = userService.findById(userId);

        Role role = optionalUser.get().getRole();
        boolean isPromotionalDate = (promotionalDateService.findByDate(createdAt));
        CartType type = getCartType(role, isPromotionalDate);

        Cart cart = mapToEntity(createdAt, optionalUser.get(), type);
        cart = cartRepository.save(cart);

        return new CartResponseDTO(cart.getId(), "CREADO", "Carrito creado exitosamente.");
    }
    @Transactional
    public void addItemToCart(Long cartId, ItemRequestDTO itemRequestDTO, Long userId) {
        Cart cart = getCart(cartId);
        validateUser(cart.getUser().getId(), userId);
        itemService.addItem(cart, itemRequestDTO);
    }
    @Transactional
    public void deleteItemFromCart(Long cartId, Long itemId, Long userId) {
        Cart cart = getCart(cartId);
        validateUser(cart.getUser().getId(), userId);
        itemService.deleteItem(itemId, cart);
    }
    @Transactional
    public void modifyItemFromCart(Long cartId, Long itemId, ModifyItemRequestDTO requestDTO, Long userId) {
        Cart cart = getCart(cartId);
        validateUser(cart.getUser().getId(), userId);
        itemService.modifyItem(itemId, cart, requestDTO);
    }
    @Transactional
    public void checkoutCart(Long cartId, Long userId) {
        Cart cart = getCart(cartId);
        validateUser(cart.getUser().getId(), userId);
        if (cart.isConfirmed()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El carrito ya fue confirmado.");
        }
        cart.setConfirmed(true);
        List<Item> items =  itemService.getItems(cart.getId());
        setCartTotal(cart, items);
        cartRepository.save(cart);
    }
    @Transactional
    public void deleteCart(Long cartId, Long userId) {
        Cart cart = getCart(cartId);
        validateUser(cart.getUser().getId(), userId);
        if (cart.isDeleted()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El carrito ya está eliminado.");
        }
        cart.setDeleted(true);
        cartRepository.save(cart);
    }

    public CartDetailResponseDTO getDetailCart(Long cartId, Long userId) {

        Cart cart = getCart(cartId);
        validateUser(cart.getUser().getId(), userId);

        List<Item> items =  itemService.getItems(cart.getId());
        int totalProducts = items.stream().mapToInt(Item::getAmount).sum();

        if (cart.getTotal() == 0) {
            setCartTotal(cart, items);
        }

        return mapToDTO(cart, items, totalProducts);
    }

    private void validateUser(Long cartId, Long userId) {
        if (userId != cartId) {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }
    }

    private CartDetailResponseDTO mapToDTO(Cart cart, List<Item> items, int totalProducts) {
        List<ItemResponseDTO> itemDTOs = items.stream()
                .map(item -> new ItemResponseDTO(
                        item.getId(),
                        item.getProduct().getName(),
                        item.getProduct().getId(),
                        item.getPrice(),
                        item.getAmount()))
                .toList();

        return new CartDetailResponseDTO(
                cart.getId(),
                cart.getTotal(),
                totalProducts,
                cart.isDeleted(),
                cart.isConfirmed(),
                itemDTOs
        );
    }

    public void setCartTotal(Cart cart, List<Item> items) {
        double total = calculateTotal(cart, items);
        cart.setTotal(total);
    }
    public double getCartTotal(Long cartId, Long userId) {
        Cart cart = getCart(cartId);
        validateUser(cart.getUser().getId(), userId);
        List<Item> items =  itemService.getItems(cart.getId());

        return calculateTotal(cart, items);
    }
    private double calculateTotal(Cart cart, List<Item> items) {
        int amountProducts = items.stream().mapToInt(Item::getAmount).sum();
        double totalWithoutDiscount = items.stream().mapToDouble(item -> item.getPrice() * item.getAmount()).sum();

        double finalTotal = totalWithoutDiscount;

        if (cart.getType() == CartType.VIP) {                            // Cliente VIP se le bonifica el producto más barato y descuenta $500
            double cheapestPrice = Double.MAX_VALUE;
            for (Item item : items) {
                if (item.getPrice() < cheapestPrice) {
                    cheapestPrice = item.getPrice();
                }
            }
            double discount = cheapestPrice + 500.0;
            finalTotal = Math.max(0, totalWithoutDiscount - discount);  // Hace los descuentos y nunca devuelve 0
        } else if  (cart.getType() == CartType.SPECIAL_DATE) {          // Fechas especiales descuento general de $300
            finalTotal = Math.max(0, totalWithoutDiscount - 300.0);
        } else {
            if (amountProducts == 4) {                                  // Exactamente 4 productos 25% de descuento
                finalTotal = totalWithoutDiscount * 0.75;
            } else if (amountProducts > 10) {                           // Más de 10 productos descuenta $100
                finalTotal = Math.max(0, totalWithoutDiscount - 100.0);
            }
        }
        return finalTotal;
    }

    private Cart mapToEntity(String createdAt, UserEntity user, CartType type) {
        LocalDateTime date = Utils.parseToLocalDateTime(createdAt);
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

    private Cart getCart(Long cartId) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found or doesn't exists");
        }
        return optionalCart.get();
    }
}

