package com.luciana.challenge_factorIT.controllers;

import com.luciana.challenge_factorIT.dtos.requests.AuthRequestDTO;
import com.luciana.challenge_factorIT.dtos.responses.AuthResponseDTO;
import com.luciana.challenge_factorIT.security.jwt.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody AuthRequestDTO requestDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword())
        );
        String token = jwtUtil.generateToken(requestDTO.getUsername());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }
}
