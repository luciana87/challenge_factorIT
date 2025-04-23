package com.luciana.challenge_factorIT.dtos.responses;

public class CartResponseDTO {
    private Long cartId;
    private String status;
    private String message;

    public CartResponseDTO() {
    }

    public CartResponseDTO(Long cartId, String status, String message) {
        this.cartId = cartId;
        this.status = status;
        this.message = message;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
