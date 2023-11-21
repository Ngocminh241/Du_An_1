package com.example.du_an_1.DTO;

public class Type_Of_Food {
    private int maLoai;
    private String tenLoai;

    public Type_Of_Food(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public Type_Of_Food() {
    }

    public Type_Of_Food(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getId() {
        return maLoai;
    }

    public void setId(int id) {
        this.maLoai = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
