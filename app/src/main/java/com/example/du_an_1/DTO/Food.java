package com.example.du_an_1.DTO;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;

public class Food implements Serializable {
    private String maFood;
    private int maLoai;
    private String tenFood;
    private int giaFood;
    private byte[] hinhAnh;
    private String moTa;
    private int trangthai;
    private int numberInCart;



    public Food(){

    }
    public Food(String maFood, int maLoai, String tenFood, int giaFood, byte[] hinhAnh, String moTa, int trangthai) {
        this.maFood = maFood;
        this.maLoai = maLoai;
        this.tenFood = tenFood;
        this.giaFood = giaFood;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.trangthai = trangthai;
    }


    public Food(String maFood, int maLoai, String tenFood, int giaFood, byte[] hinhAnh, String moTa, int trangthai, int numberInCart) {
        this.maFood = maFood;
        this.maLoai = maLoai;
        this.tenFood = tenFood;
        this.giaFood = giaFood;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.numberInCart = numberInCart;
    }


    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String getMaFood() {
        return maFood;
    }

    public void setMaFood(String maFood) {
        this.maFood = maFood;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenFood() {
        return tenFood;
    }

    public void setTenFood(String tenFood) {
        this.tenFood = tenFood;
    }

    public int getGiaFood() {
        return giaFood;
    }

    public void setGiaFood(int giaFood) {
        this.giaFood = giaFood;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }


    public Uri hienthi(Context context) {
        byte[] imageData = getHinhAnh();// Mảng byte chứa dữ liệu hình ảnh
        String tempFileName = "temp_image.jpg";
        Uri uri;
        // Tạo đường dẫn tới tập tin ảnh tạm
        File tempFile = new File(context.getCacheDir(), tempFileName);
        // Ghi dữ liệu blob vào tập tin ảnh tạm
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            fileOutputStream.write(imageData);
            fileOutputStream.close();
            uri = Uri.fromFile(tempFile);
            return uri;
        } catch (Exception e) {
            return null;
        }
    }


}


