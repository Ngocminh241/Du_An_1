package com.example.du_an_1.DTO;

public class GioHang {
    private int id_DonHang;
    private String ngayDat;
    private int trangThai_TT;
    private int tong_gia;
    private int maUser;

    public GioHang() {
    }

    public GioHang(int id_DonHang, String ngayDat, int trangThai_TT, int tong_gia, int maUser) {
        this.id_DonHang = id_DonHang;
        this.ngayDat = ngayDat;
        this.trangThai_TT = trangThai_TT;
        this.tong_gia = tong_gia;
        this.maUser = maUser;
    }

    public int getId_DonHang() {
        return id_DonHang;
    }

    public void setId_DonHang(int id_DonHang) {
        this.id_DonHang = id_DonHang;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getTrangThai_TT() {
        return trangThai_TT;
    }

    public void setTrangThai_TT(int trangThai_TT) {
        this.trangThai_TT = trangThai_TT;
    }

    public int getTong_gia() {
        return tong_gia;
    }

    public void setTong_gia(int tong_gia) {
        this.tong_gia = tong_gia;
    }

    public int getMaUser() {
        return maUser;
    }

    public void setMaUser(int maUser) {
        this.maUser = maUser;
    }
}
