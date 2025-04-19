package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public Optional<UserEntity> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
