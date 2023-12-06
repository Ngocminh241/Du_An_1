package com.example.du_an_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.Database.DbHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class DAO_GioHang {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public DAO_GioHang(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public Cursor getCart(int userId){
        return dbHelper.getDataRow("SELECT ID_DON_HANG FROM gioHang WHERE status='Craft' AND maUser=" + userId);
    }
//    int maDH;
//    public String getCart(int userId) {
//        try {
//            Cursor cursor = db.rawQuery("SELECT ID_DON_HANG FROM gioHang WHERE status='Craft' AND maUser="+userId, new String[] {String.valueOf(mafod)});
//            if (cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    maDH = Integer.parseInt(cursor.getString(0));
//                    cursor.moveToNext();
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Lỗi" + e);
//        }
//        return String.valueOf(maDH);
//    }
    public Cursor getCart2(int giohangId){
        return dbHelper.getDataRow("SELECT maUser FROM gioHang WHERE status='Craft' AND ID_DON_HANG=" + giohangId);
    }
    public Cursor getCart3(int ctdonId){
        return dbHelper.getDataRow("SELECT ID_DON_HANG FROM gioHang WHERE status='Craft' AND id_ctdh=" + ctdonId);
    }
    public long addOrder(GioHang order) {
        ContentValues values = new ContentValues();
        values.put("id_ctdh", order.getId_ct());
        values.put("maUser", order.getUserId());
        values.put("address", order.getAddress());
        values.put("NGAY_DAT", order.getDateOfOrder());
        values.put("TONG_GIA",order.getTotalValue());
        values.put("status",order.getStatus());
        return db.insert("gioHang", null, values);
    }
    public String getDate(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        return day + "/" + month + "/" + year;
    }
    public void updateOrder(GioHang order){
        String query = "UPDATE gioHang SET address='" + order.getAddress() +
                "', NGAY_DAT='" + order.getDateOfOrder() +
                "', TONG_GIA=" + order.getTotalValue() +
                ", status='" + order.getStatus() +
                "' WHERE ID_DON_HANG=" + order.getId() +
                " AND maUser=" + order.getUserId();
        dbHelper.queryData(query);
    }
    public ArrayList<GioHang> getOrderOfUser(Integer userId, String status){
        ArrayList<GioHang> orderList = new ArrayList<>();
        String query = "SELECT * FROM (SELECT * FROM gioHang WHERE maUser=" + userId + ") WHERE status='" + status + "'";
        if(status.equals("Delivered")){
            query += " OR status='Canceled'";
        }
        Cursor cursor = dbHelper.getData(query);
        while (cursor.moveToNext()){
            orderList.add(new GioHang(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getInt(5),
                    cursor.getString(6)));
        }
        return orderList;
    }
    public Integer quantityOfOrder(){
        String query = "SELECT COUNT(*) FROM gioHang WHERE status='Delivered'";
        Cursor cursor = dbHelper.getDataRow(query);
        return cursor.getInt(0);
    }
}