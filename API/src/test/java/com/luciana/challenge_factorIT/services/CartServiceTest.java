package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.entities.Cart;
import com.luciana.challenge_factorIT.entities.Item;
import com.luciana.challenge_factorIT.entities.Product;
import com.luciana.challenge_factorIT.enums.CartType;
import com.luciana.challenge_factorIT.enums.Category;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartServiceTest {
    private final CartService cartService;

    public CartServiceTest(CartService cartService) {
        this.cartService = cartService;
    }

    @Test
    void testCalculateTotal_VipCartWithMoreThan10Products() {
        Cart cart = new Cart();
        cart.setType(CartType.VIP);

        Product product1 = new Product(1L, "Monitor", 300000, "Descripción monitor", Category.ELECTRONICA,false);
        Product product2 = new Product(2L, "Acuarelas", 18500, "Descripción acuarelas", Category.LIBRERIA,false);

        List<Item> items = List.of(
                new Item(300000, 1, product1, cart),
                new Item(37000, 2, product2, cart)
        );

        double total = cartService.calculateTotal(cart, items);
        // producto más barato = 18500 + 500 de bonificación => descuento 19000
        assertEquals(19000.0, total);
    }
}
