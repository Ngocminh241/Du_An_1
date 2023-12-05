package com.example.du_an_1.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.DTO.chitietDonHang;
import com.example.du_an_1.Database.DbHelper;

import java.util.ArrayList;

public class DAO_chitietDonHang {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public DAO_chitietDonHang(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public chitietDonHang getExistOrderDetail(Integer orderId){
        String query = "SELECT * FROM CHI_TIET_DON_HANG WHERE id_ctdh=" + orderId;
        Cursor cursor = dbHelper.getDataRow(query);
        if(cursor.moveToFirst()) {
            chitietDonHang orderDetail = new chitietDonHang(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3));
            System.out.println(orderDetail);
            return orderDetail;
        }
        return null;
    }
    public boolean updateQuantity(chitietDonHang orderDetail){
        String query = "UPDATE CHI_TIET_DON_HANG SET SO_LUONG=" + orderDetail.getQuantity() +
                " WHERE id_ctdh=" + orderDetail.getOrderId() +
                " AND maFood=" + orderDetail.getFoodId();
        try {
            dbHelper.queryData(query);
            return true;
        } catch (Exception err){
            return false;
        }
    }
    public boolean addOrderDetail(chitietDonHang od) {
        String query = "INSERT INTO CHI_TIET_DON_HANG VALUES(" +
                od.getOrderId() + ", " +
                od.getFoodId() + ", " +
                od.getPrice() + ", " +
                od.getQuantity() + ")";
        try {
            dbHelper.queryData(query);
            return true;
        } catch (Exception err){
            return false;
        }
    }
    public ArrayList<chitietDonHang> getCartDetailList(Integer orderId){
        ArrayList<chitietDonHang> orderDetailArrayList = new ArrayList<>();
        String query = "SELECT * FROM CHI_TIET_DON_HANG WHERE id_ctdh=" + orderId;
        Cursor cursor = dbHelper.getData(query);
        while(cursor.moveToNext()){
            orderDetailArrayList.add(new chitietDonHang(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getInt(3)));
        }
        return orderDetailArrayList;
    }
    public boolean deleteOrderDetailByOrderIdAndFoodId(Integer orderId, Integer foodId) {
        String query = "DELETE FROM CHI_TIET_DON_HANG WHERE maFood=" +
                foodId + " and id_ctdh=" + orderId;
        try {
            dbHelper.queryData(query);
            return true;
        } catch (Exception err){
            return false;
        }
    }
}
