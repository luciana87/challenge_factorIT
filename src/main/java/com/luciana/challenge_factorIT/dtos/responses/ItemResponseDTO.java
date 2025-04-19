package com.luciana.challenge_factorIT.dtos.responses;

public class ItemResponseDTO {
    private Long id;
    private String productName;
    private double unitPrice;
    private int amount;

    public ItemResponseDTO() {
    }

    public ItemResponseDTO(Long id, String productName, double unitPrice, int amount) {
        this.id = id;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
