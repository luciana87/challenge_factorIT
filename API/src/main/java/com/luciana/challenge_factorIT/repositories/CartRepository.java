package com.luciana.challenge_factorIT.repositories;

import com.luciana.challenge_factorIT.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findAllById(Long userId);

    List<Cart> findByUserIdAndCreatedAtBetween(Long id, LocalDateTime start, LocalDateTime end);
}
