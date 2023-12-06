package com.example.du_an_1.DTO;

import java.io.Serializable;

public class GioHang implements Serializable {
    private int id;
    private int id_ct;
    private int userId;
    private String address;
    private String dateOfOrder;
    private int totalValue;
    private String status;


    public GioHang(int id,int id_ct, int userId, String address, String dateOfOrder, int totalValue, String status) {
        this.id = id;
        this.id_ct = id_ct;
        this.userId = userId;
        this.address = address;
        this.dateOfOrder = dateOfOrder;
        this.totalValue = totalValue;
        this.status = status;
    }
    public GioHang(int id, int userId, String address, String dateOfOrder, int totalValue, String status) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.dateOfOrder = dateOfOrder;
        this.totalValue = totalValue;
        this.status = status;
    }

    public int getId_ct() {
        return id_ct;
    }

    public void setId_ct(int id_ct) {
        this.id_ct = id_ct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(String dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
