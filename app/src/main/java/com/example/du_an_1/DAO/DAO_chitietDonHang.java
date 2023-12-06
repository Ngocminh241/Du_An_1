package com.example.du_an_1.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_1.DTO.chitietDonHang;
import com.example.du_an_1.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO_chitietDonHang {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public DAO_chitietDonHang(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public chitietDonHang getExistOrderDetail(int orderId){
        String query = "SELECT * FROM CHI_TIET_DON_HANG WHERE id_ctdh =" +orderId;
        Cursor cursor = dbHelper.getData(query);
        if(cursor.moveToFirst()) {
            chitietDonHang orderDetail = new chitietDonHang(
                    cursor.getInt(0),
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
                " WHERE id_ctdh=" + orderDetail.getOrderId();
        try {
            dbHelper.queryData(query);
            return true;
        } catch (Exception err){
            return false;
        }
    }
    public boolean addOrderDetail(chitietDonHang od) {
        ContentValues values = new ContentValues();
        values.put("id_ctdh",od.getOrderId());
        values.put("maFood",od.getFoodId());
        values.put("GIA",od.getPrice());
        values.put("SO_LUONG",od.getQuantity());
        try {
            db.insert("CHI_TIET_DON_HANG", null, values);
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
//    public List<chitietDonHang> getCartDetailList(int orderId) {
//        List<chitietDonHang> list = new ArrayList<>();
//        Cursor c = db.rawQuery("SELECT * FROM CHI_TIET_DON_HANG  WHERE id_ctdh="+orderId, null);
//        if (c != null && c.getCount() > 0) {
//            c.moveToFirst();
//            do {
//                int mactdon = c.getInt(0);
//                String mafood = c.getString(1);
//                int giaFood = c.getInt(2);
//                int soluong = c.getInt(3);
//                list.add(new chitietDonHang(mactdon, mafood, giaFood, soluong));
//            } while (c.moveToNext());
//        }
//        return list;
//    }
    public boolean deleteOrderDetailByOrderIdAndFoodId(int orderId) {
        String query = "DELETE FROM CHI_TIET_DON_HANG WHERE id_ctdh=" + orderId;
        try {
            dbHelper.queryData(query);
            return true;
        } catch (Exception err){
            return false;
        }
    }
//    public int deleteOrderDetailByOrderIdAndFoodId(chitietDonHang SP) {
//        String[] index = new String[]{
//                String.valueOf(SP.getOrderId())
//        };
//        return db.delete("CHI_TIET_DON_HANG", "id_ctdh=?", index);
//    }
    int maDH;
    public String getIdDH(String mafod) {
        try {
            Cursor cursor = db.rawQuery("SELECT id_ctdh FROM CHI_TIET_DON_HANG WHERE maFood=?", new String[] {String.valueOf(mafod)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    maDH = Integer.parseInt(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return String.valueOf(maDH);
    }
    String mafod;
    public String getIdmF(int madh) {
        try {
            Cursor cursor = db.rawQuery("SELECT maFood FROM CHI_TIET_DON_HANG WHERE id_ctdh="+madh, new String[] {String.valueOf(madh)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    mafod = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return String.valueOf(mafod);
    }
    public List<chitietDonHang> getAllAA() {
        List<chitietDonHang> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM CHI_TIET_DON_HANG INNER JOIN Food on CHI_TIET_DON_HANG.maFood = Food.maFood", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int mactdon = c.getInt(0);
                String mafood = c.getString(1);
                int giaFood = c.getInt(2);
                int soluong = c.getInt(3);
                list.add(new chitietDonHang(mactdon, mafood, giaFood, soluong));
            } while (c.moveToNext());
        }
        return list;
    }
}
