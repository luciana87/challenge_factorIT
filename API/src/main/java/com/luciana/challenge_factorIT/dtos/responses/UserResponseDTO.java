package com.luciana.challenge_factorIT.dtos.responses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.luciana.challenge_factorIT.entities.Cart;
import com.luciana.challenge_factorIT.entities.Vip;
import com.luciana.challenge_factorIT.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String dni;
    private String username;
    private boolean deleted;
    @JsonAlias("deleted_at")
    private LocalDateTime deletedAt;
    private Role role;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String name, String surname, String dni, String username, boolean deleted,
                           LocalDateTime deletedAt, Role role
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dni = dni;
        this.username = username;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
