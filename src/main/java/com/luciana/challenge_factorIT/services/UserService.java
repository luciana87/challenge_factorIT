package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.dtos.responses.UserResponseDTO;
import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.enums.Role;
import com.luciana.challenge_factorIT.repositories.UserRepository;

import org.springframework.http.HttpStatus;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole().name())
                .build();
    }
}
