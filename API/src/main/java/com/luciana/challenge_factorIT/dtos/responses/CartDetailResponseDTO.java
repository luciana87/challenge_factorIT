package com.luciana.challenge_factorIT.dtos.responses;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;
import java.util.List;

public class CartDetailResponseDTO {
    private Long id;
    private double total;
    private int totalProducts;
    private boolean deleted;
    private List<ItemResponseDTO> itemsDTO;

    public CartDetailResponseDTO() {
    }

    public CartDetailResponseDTO(Long id, double total, int totalProducts, boolean deleted, List<ItemResponseDTO> itemsDTO) {
        this.id = id;
        this.total = total;
        this.totalProducts = totalProducts;
        this.deleted = deleted;
        this.itemsDTO = itemsDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<ItemResponseDTO> getItemsDTO() {
        return itemsDTO;
    }

    public void setItemsDTO(List<ItemResponseDTO> itemsDTO) {
        this.itemsDTO = itemsDTO;
    }
}
