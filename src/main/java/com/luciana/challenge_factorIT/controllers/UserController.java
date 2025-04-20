package com.luciana.challenge_factorIT.controllers;

import com.luciana.challenge_factorIT.dtos.responses.UserResponseDTO;
import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.entities.Vip;
import com.luciana.challenge_factorIT.services.UserService;
import com.luciana.challenge_factorIT.services.VipService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final VipService vipService;

    public UserController(UserService userService, VipService vipService) {
        this.userService = userService;
        this.vipService = vipService;
    }
    @GetMapping("/vip")

    public ResponseEntity<List<UserResponseDTO>> getVipUsers() {
        try {
            List<UserResponseDTO> users = userService.getVipsUsers();
            return new ResponseEntity<List<UserResponseDTO>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vip/by-month")
    public ResponseEntity<List<UserResponseDTO>> getVipUsersByMonth(@RequestParam @Valid boolean active,
                                                               @RequestParam @Valid int year,
                                                               @RequestParam @Valid int month
    ) {
        try {
            List<Vip> vips = vipService.findByActiveAndDate(active, year, month);
            List<UserResponseDTO> users = vips.stream().map(vip -> userService.mapToDTO(vip.getUser())).toList();
            return new ResponseEntity<List<UserResponseDTO>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
