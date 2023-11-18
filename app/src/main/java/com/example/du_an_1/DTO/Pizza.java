package com.example.du_an_1.DTO;

public class Pizza {
    private int maPizza;
    private String TenPizza;
    private int giaPizza;
    private byte[] hinhAnh;

    public Pizza() {
    }

    public int getMaPizza() {
        return maPizza;
    }

    public void setMaPizza(int maPizza) {
        this.maPizza = maPizza;
    }

    public String getTenPizza() {
        return TenPizza;
    }

    public void setTenPizza(String tenPizza) {
        TenPizza = tenPizza;
    }

    public int getGiaPizza() {
        return giaPizza;
    }

    public void setGiaPizza(int giaPizza) {
        this.giaPizza = giaPizza;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}


