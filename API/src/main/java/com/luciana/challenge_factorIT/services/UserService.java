package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.dtos.responses.UserResponseDTO;
import com.luciana.challenge_factorIT.entities.Cart;
import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.enums.Role;
import com.luciana.challenge_factorIT.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> findById(Long userId) {
        Optional<UserEntity> optionalUser =  userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or doesn't exists");
        }
        return optionalUser;
    }

    public List<UserResponseDTO> getVipsUsers() {
        List<UserEntity> users = userRepository.findAllByRole(Role.VIP);
        return users.stream().map(userEntity -> mapToDTO(userEntity)).toList();
    }

    public UserResponseDTO mapToDTO(UserEntity userEntity) {
        return new UserResponseDTO(userEntity.getId(), userEntity.getName(), userEntity.getSurname(), userEntity.getDni(),
                userEntity.getUsername(), userEntity.isDeleted(), userEntity.getDeletedAt(), userEntity.getRole());
    }

    @Transactional
    public void createUsers() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        List<UserEntity> users = List.of(
                new UserEntity("Ana", "García", "12345678", "uservip", encoder.encode("uservip"), false, null, Role.VIP),
                new UserEntity("Luis", "Martínez", "23456789", "uservip1", encoder.encode("uservip1"), false, null, Role.VIP),
                new UserEntity("Lucía", "Pérez", "34567890", "admin1", encoder.encode("admin1"), false, null, Role.ADMIN),
                new UserEntity("Carlos", "Rodríguez", "45678901", "user1", encoder.encode("user1"), false, null, Role.COMMON),
                new UserEntity("María", "López", "56789012", "user2", encoder.encode("user2"), true, null, Role.COMMON),
                new UserEntity("Juan", "Fernández", "67890123", "user3", encoder.encode("user3"), false, null, Role.COMMON)
        );

        users.forEach(userRepository::save);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAllExcludingAdmin();
    }

    public void save(UserEntity user) {
        userRepository.save(user);
    }
}
