package com.luciana.challenge_factorIT.dtos.responses;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.luciana.challenge_factorIT.enums.Role;

import java.time.LocalDateTime;

public class VipResponseDTO {
    private Long id;
    private String userName;
    private String userSurname;
    private String username;
    private boolean deleted;
    @JsonAlias("deleted_at")
    private LocalDateTime modifiedAt;

    public VipResponseDTO() {
    }

    public VipResponseDTO(Long id, String userName, String userSurname, String username,  boolean deleted, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.userSurname = userSurname;
        this.username = username;
        this.deleted = deleted;
        this.modifiedAt = modifiedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
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

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
