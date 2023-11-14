package com.example.du_an_1.DTO;

public class User {
    private int maUser;
    private String maDN;
    private String matKhau;
    private String hoTen;
    private String sDT;

    public User() {
    }

    public User(int maUser, String maDN, String matKhau, String hoTen, String sDT) {
        this.maUser = maUser;
        this.maDN = maDN;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.sDT = sDT;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }

    public String getMaDN() {
        return maDN;
    }

    public void setMaDN(String maDN) {
        this.maDN = maDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }
}
