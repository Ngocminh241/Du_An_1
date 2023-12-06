package com.example.du_an_1.DTO;

public class chitietDonHang {
    private int orderId;
    private String foodId;
    private int price;
    private int quantity;



    public chitietDonHang(int orderId, String foodId, int price, int quantity) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
