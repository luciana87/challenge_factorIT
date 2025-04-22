package com.luciana.challenge_factorIT.repositories;

import com.luciana.challenge_factorIT.entities.Cart;
import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

    List<UserEntity> findAllByRole(Role role);

    boolean existsByUsername(String admin);
    @Query("SELECT u FROM UserEntity u WHERE u.role <> 'ADMIN'")
    List<UserEntity> findAllExcludingAdmin();
}
