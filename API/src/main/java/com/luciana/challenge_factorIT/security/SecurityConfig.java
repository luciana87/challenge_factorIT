package com.luciana.challenge_factorIT.security;

import com.luciana.challenge_factorIT.entities.UserEntity;
import com.luciana.challenge_factorIT.enums.Role;
import com.luciana.challenge_factorIT.repositories.UserRepository;
import com.luciana.challenge_factorIT.security.jwt.JwtAuthFilter;
import com.luciana.challenge_factorIT.services.UserAuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthFilter jwtAuthFilter) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/user/create/**","user/vip/update").permitAll()  // Rutas públicas
                        .requestMatchers("/carts/create**", "/products**", "/get-cart/**").authenticated()  // Rutas privadas
                        .requestMatchers("/user/vip**", "/user/vip/by-month**").hasRole("ADMIN")
                        .anyRequest().authenticated()  // Otras rutas requieren autenticación
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder,
                                                       UserAuthService userAuthService) throws Exception {
        AuthenticationManagerBuilder auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth
                .userDetailsService(userAuthService)
                .passwordEncoder(passwordEncoder);
        return auth.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (!userRepository.existsByUsername("admin123")) {
                UserEntity user = new UserEntity();
                user.setName("Admin123");
                user.setSurname("Prueba");
                user.setDni("31333333");
                user.setUsername("admin123");
                user.setPassword(encoder.encode("admin123"));
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            }
        };
    }
}
