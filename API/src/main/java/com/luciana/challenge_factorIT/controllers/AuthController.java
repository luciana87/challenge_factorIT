package com.luciana.challenge_factorIT.controllers;

import com.luciana.challenge_factorIT.dtos.requests.AuthRequestDTO;
import com.luciana.challenge_factorIT.dtos.responses.AuthResponseDTO;
import com.luciana.challenge_factorIT.enums.Role;
import com.luciana.challenge_factorIT.security.jwt.JwtUtil;
import com.luciana.challenge_factorIT.services.UserAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserAuthService userAuthService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserAuthService userAuthService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userAuthService = userAuthService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login (@RequestBody AuthRequestDTO requestDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword())
        );
        String token = jwtUtil.generateToken(requestDTO.getUsername());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @GetMapping("/role")
    public ResponseEntity<Role> getRole () {
        try {
            return new ResponseEntity<>(userAuthService.getRequestRole(), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
