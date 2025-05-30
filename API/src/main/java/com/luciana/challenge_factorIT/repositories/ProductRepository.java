package com.luciana.challenge_factorIT.repositories;

import com.luciana.challenge_factorIT.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByDeleted(boolean deleted);
}
