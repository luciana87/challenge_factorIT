package com.luciana.challenge_factorIT.entities;

import jakarta.persistence.*;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;

@Entity
public class PromotionalDates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;
    @Column(name = "endeded_at", nullable = false)
    private LocalDateTime endedAt;

    public PromotionalDates() {
    }

    public PromotionalDates(LocalDateTime startedAt, LocalDateTime endedAt) {
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }
}
