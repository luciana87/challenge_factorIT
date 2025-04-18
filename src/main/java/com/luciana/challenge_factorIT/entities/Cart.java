package com.luciana.challenge_factorIT.entities;

import com.luciana.challenge_factorIT.enums.CartType;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "cart_type")
    private CartType type;
    private boolean confirmed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @OneToMany(mappedBy = "cart")
    private List<Item> listItems;

    public Cart() {
    }

    public Cart(double total, UserEntity user, CartType type, boolean confirmed, List<Item> listItems) {
        this.total = total;
        this.createdAt = LocalDateTime.now();
        this.user = user;
        this.type = type;
        this.confirmed = confirmed;
        this.listItems = (listItems != null) ? listItems : new ArrayList<>();
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }

    public CartType getType() {
        return type;
    }
    public void setType(CartType type) {
        this.type = type;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public List<Item> getListItems() {
        return listItems;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }
}
