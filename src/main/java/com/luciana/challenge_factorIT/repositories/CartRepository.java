package com.luciana.challenge_factorIT.repositories;

import com.luciana.challenge_factorIT.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findAllById(Long userId);
}
