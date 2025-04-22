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
    private final CartService cartService = new CartService();

    @Test
    void testCalculateTotal_VipCartWithMoreThan10Products() {
        Cart cart = new Cart();
        cart.setType(CartType.VIP);

        Product product = new Product(1, "Monitor", 300000, "Descripción monitor", Category.ELECTRONICA,false);

        List<Item> items = List.of(
                new Item(100000, 1, 5, cart),
                new Item(200000, 2, 6, cart) // total = 5*100 + 6*50 = 500 + 300 = 800
        );

        double total = cartService.calculateTotal(cart, items);
        // producto más barato = 50 + 500 de bonificación => descuento 550
        assertEquals(250.0, total);
    }

    @Test
    void testCalculateTotal_SpecialDateCartWithMoreThan10Products() {
        Cart cart = new Cart();
        cart.setType(CartType.SPECIAL_DATE);

        List<Item> items = List.of(
                new Item(1L, "Item1", 11, 20.0) // total = 220
        );

        double total = cartService.calculateTotal(cart, items);
        assertEquals(Math.max(0, 220.0 - 300.0), total); // Debe retornar 0
    }

    @Test
    void testCalculateTotal_NormalCartWithExactly4Products() {
        Cart cart = new Cart();
        cart.setType(CartType.COMMON); // asumo que existe REGULAR

        List<Item> items = List.of(
                new Item(1L, "Item1", 4, 100.0) // total = 400
        );

        double total = cartService.calculateTotal(cart, items);
        assertEquals(300.0, total); // 25% de descuento
    }

    @Test
    void testCalculateTotal_NormalCartWithMoreThan10Products() {
        Cart cart = new Cart();
        cart.setType(CartType.COMMON);

        List<Item> items = List.of(
                new Item(1L, "Item1", 11, 10.0) // total = 110
        );

        double total = cartService.calculateTotal(cart, items);
        assertEquals(10.0, total); // 110 - 100
    }

    @Test
    void testCalculateTotal_NoDiscount() {
        Cart cart = new Cart();
        cart.setType(CartType.COMMON);

        List<Item> items = List.of(
                new Item(1L, "Item1", 2, 50.0) // total = 100
        );

        double total = cartService.calculateTotal(cart, items);
        assertEquals(100.0, total); // sin descuentos
    }
}
