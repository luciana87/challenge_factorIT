package com.luciana.challenge_factorIT.dtos.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public class CartRequestDTO {

    @NotNull(message = "La fecha de creación no puede ser nula")
    @JsonAlias("created_at")
    private String createdAt;
    @NotNull
    @JsonAlias("user_id")
    private Long userId;

    public CartRequestDTO() {
    }

    public CartRequestDTO(String createdAt, Long userId) {
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

