package com.luciana.challenge_factorIT.dtos.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;

public class CartRequestDTO {

    @NotNull(message = "La fecha de creación no puede ser nula")
    @JsonAlias("created_at")
    private String createdAt;

    public CartRequestDTO() {
    }

    public CartRequestDTO(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

}

