package com.luciana.challenge_factorIT.controllers;

import com.luciana.challenge_factorIT.dtos.responses.ItemResponseDTO;
import com.luciana.challenge_factorIT.entities.Item;
import com.luciana.challenge_factorIT.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDTO> getItem (@PathVariable Long itemId) {
        try {
            Item item =itemService.getItem(itemId);

            return new ResponseEntity<>(itemService.mapToDTO(item), HttpStatus.OK);
        } catch (
        ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        } catch (
        AccessDeniedException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
