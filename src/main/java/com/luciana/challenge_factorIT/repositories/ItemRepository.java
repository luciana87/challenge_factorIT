package com.luciana.challenge_factorIT.repositories;

import com.luciana.challenge_factorIT.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllItemsByCartId(Long cartId);
}
