package com.luciana.challenge_factorIT.entities;

import com.luciana.challenge_factorIT.enums.Category;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private double price;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    private boolean deleted;
    @OneToMany(mappedBy = "product")
    private List<Item> listItems;

    public Product() {
    }

    public Product(String name, double price, String description, Category category, List<Item> listItems) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.deleted = false;
        this.listItems = (listItems != null) ? listItems : new ArrayList<>();
    }

    public Product(Long id, String name, double price, String description, Category category, boolean deleted) {
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

    public List<Item> getListItems() {
        return listItems;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }
}
