package com.luciana.challenge_factorIT.dtos.responses;

import com.luciana.challenge_factorIT.entities.Item;
import com.luciana.challenge_factorIT.enums.Category;

import java.util.List;

public class ProductResponseDTO {
    private Long id;
    private String name;
    private double price;
    private String description;
    private Category category;
    private boolean deleted;

    public ProductResponseDTO() {
    }

    public ProductResponseDTO(Long id, String name, double price, String description, Category category, boolean deleted) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.deleted = deleted;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
