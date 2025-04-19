package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.dtos.responses.UserResponseDTO;
import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.entities.Vip;
import com.luciana.challenge_factorIT.enums.Role;
import com.luciana.challenge_factorIT.repositories.UserRepository;
import com.luciana.challenge_factorIT.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

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
}
