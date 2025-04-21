package com.luciana.challenge_factorIT.dtos.requests;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemRequestDTO {
    @NotNull
    @JsonAlias("product_id")
    private Long productId;
    @NotNull
    @Min(value = 1, message = "La cantidad debe ser mayor que 0")
    private int amount;

    public ItemRequestDTO() {
    }

    public ItemRequestDTO(Long productId, int amount) {
        this.productId = productId;
        this.amount = amount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
