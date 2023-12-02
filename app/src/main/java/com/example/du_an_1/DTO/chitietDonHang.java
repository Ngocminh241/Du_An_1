package com.example.du_an_1.DTO;

public class chitietDonHang {
    private int id_CTDonHang;
    private int id_DonHang;
    private String maFood;
    private int soLuong;
    private int gia;

    public chitietDonHang() {
    }

    public chitietDonHang(int id_CTDonHang, int id_DonHang, String maFood, int soLuong, int gia) {
        this.id_CTDonHang = id_CTDonHang;
        this.id_DonHang = id_DonHang;
        this.maFood = maFood;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public int getId_CTDonHang() {
        return id_CTDonHang;
    }

    public void setId_CTDonHang(int id_CTDonHang) {
        this.id_CTDonHang = id_CTDonHang;
    }

    public int getId_DonHang() {
        return id_DonHang;
    }

    public void setId_DonHang(int id_DonHang) {
        this.id_DonHang = id_DonHang;
    }

    public String getMaFood() {
        return maFood;
    }

    public void setMaFood(String maFood) {
        this.maFood = maFood;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
