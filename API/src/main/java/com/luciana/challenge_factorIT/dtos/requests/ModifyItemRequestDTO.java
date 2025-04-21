package com.luciana.challenge_factorIT.dtos.requests;

import jakarta.validation.constraints.Min;

public class ModifyItemRequestDTO {
    @Min(1)
    private int amount;

    public ModifyItemRequestDTO() {
    }

    public ModifyItemRequestDTO(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
