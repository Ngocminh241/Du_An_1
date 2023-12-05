package com.example.du_an_1.DTO;

public class chitietDonHang {
    private Integer orderId;
    private String foodId;
    private int price;
    private Integer quantity;

    public chitietDonHang(Integer orderId, String foodId, int price) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.price = price;
        this.quantity = 1;
    }

    public chitietDonHang(Integer orderId, String foodId, int price, Integer quantity) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
