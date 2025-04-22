package com.luciana.challenge_factorIT.services;

import com.luciana.challenge_factorIT.dtos.responses.VipResponseDTO;
import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.entities.Vip;
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
    private final VipService vipService;

    public UserService(UserRepository userRepository, VipService vipService) {
        this.userRepository = userRepository;
        this.vipService = vipService;
    }

    public Optional<UserEntity> findById(Long userId) {
        Optional<UserEntity> optionalUser =  userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found or doesn't exists");
        }
        return optionalUser;
    }

    public List<VipResponseDTO> getVipsUsers() {
        List<Vip> vips = vipService.findAll();
        return vips.stream().map(vip -> mapToDTO(vip)).toList();
    }

    public VipResponseDTO mapToDTO(Vip vip) {
        UserEntity user = vip.getUser();
        return new VipResponseDTO(
                vip.getId(),
                user.getName(),
                user.getSurname(),
                user.getUsername(),
                user.isDeleted(),
                vip.getModifiedAt()
        );
    }

    @Transactional
    public void createUsers() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        List<UserEntity> users = List.of(
                new UserEntity("Lucía", "Pérez", "34567890", "admin1", encoder.encode("admin1"), false, null, Role.ADMIN),
                new UserEntity("Ana", "García", "12345678", "user1", encoder.encode("user1"), false, null, Role.VIP),
                new UserEntity("Luis", "Martínez", "23456789", "user2", encoder.encode("user2"), false, null, Role.VIP),
                new UserEntity("Carlos", "Rodríguez", "45678901", "user3", encoder.encode("user3"), false, null, Role.COMMON),
                new UserEntity("María", "López", "56789012", "user4", encoder.encode("user4"), true, null, Role.COMMON),
                new UserEntity("Juan", "Fernández", "67890123", "user5", encoder.encode("user5"), false, null, Role.COMMON)
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
