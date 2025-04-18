package com.luciana.challenge_factorIT.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "promotional_dates")
public class PromotionalDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;
    @Column(name = "ended_at", nullable = false)
    private LocalDateTime endedAt;

    public PromotionalDate() {
    }

    public PromotionalDate(LocalDateTime startedAt, LocalDateTime endedAt) {
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
