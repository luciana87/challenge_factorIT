package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.dtos.requests.ItemRequestDTO;
import com.luciana.challenge_factorIT.dtos.requests.ModifyItemRequestDTO;
import com.luciana.challenge_factorIT.entities.Cart;
import com.luciana.challenge_factorIT.entities.Item;
import com.luciana.challenge_factorIT.entities.Product;
import com.luciana.challenge_factorIT.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ProductService productService;

    public ItemService(ItemRepository itemRepository, ProductService productService) {
        this.itemRepository = itemRepository;
        this.productService = productService;
    }
    public void addItem(Cart cart, ItemRequestDTO itemRequestDTO) {
        Product product = productService.findById(itemRequestDTO.getProductId());
        validateItemExistsByName(cart, product);
        Item item = mapToEntity(cart,product,itemRequestDTO);

        itemRepository.save(item);
    }
    public void deleteItem(Long itemId, Cart cart) {
        validateItemExistsById(cart, itemId);
        itemRepository.deleteById(itemId);
    }

    public void modifyItem(Long itemId, Cart cart, ModifyItemRequestDTO requestDTO) {
        validateItemExistsById(cart, itemId);
        Item item = getItem(itemId);

        item.setAmount(requestDTO.getAmount());
        itemRepository.save(item);
    }

    public List<Item> getItems(Long cartId) {
        return itemRepository.findAllItemsByCartId(cartId);
    }

    private Item getItem(Long itemId) {
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if (optionalItem.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found or doesn't exists");
        }
        return optionalItem.get();
    }

    private void validateItemExistsById(Cart cart, Long itemId) {
        boolean exists = cart.getListItems().stream()
                .anyMatch(item -> item.getId().equals(itemId));

        if (!exists) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found in this cart");
        }
    }
    private void validateItemExistsByName(Cart cart, Product product) {
        boolean exists = cart.getListItems().stream()
                .anyMatch(item -> item.getProduct().getName().equalsIgnoreCase(product.getName()));

        if (exists) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item already exists in cart");
        }
    }

    private Item mapToEntity(Cart cart, Product product, ItemRequestDTO itemRequestDTO) {
        return new Item(product.getPrice(), itemRequestDTO.getAmount(), product, cart);
    }
}
