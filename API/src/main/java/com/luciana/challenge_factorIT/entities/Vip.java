package com.luciana.challenge_factorIT.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vips")
public class Vip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    private boolean active;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    public Vip() {
    }

    public Vip(LocalDateTime modifiedAt, boolean active, UserEntity user) {
        this.modifiedAt = modifiedAt;
        this.active = active;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }
}
