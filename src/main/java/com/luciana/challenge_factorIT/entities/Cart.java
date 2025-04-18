package Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table (name= "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private double total;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Cart() {
    }

    public Cart(double total, User user) {
        this.total = total;
        this.createdAt = LocalDateTime.now();
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
