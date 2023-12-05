package com.example.du_an_1.DAO;

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


//    int TT;
//    public int getCart(int userId) {
//        try {
//            Cursor cursor = db.rawQuery("SELECT ID_DON_HANG FROM DON_HANG WHERE maUser=" + userId, new String[] {String.valueOf(userId)});
//            if (cursor.getCount() > 0) {
//                cursor.moveToFirst();
//                while (!cursor.isAfterLast()) {
//                    TT = Integer.parseInt(cursor.getString(0));
//                    cursor.moveToNext();
//                }
//            }
//        } catch (Exception e) {
//            Log.i(TAG, "Lỗi" + e);
//        }
//        return TT;
//    }
    public Cursor getCart(int userId){
        return dbHelper.getDataRow("SELECT ID_DON_HANG FROM gioHang WHERE status='Craft' AND maUser=" + userId);
    }
    public void addOrder(GioHang order) {
        String query = "INSERT INTO gioHang VALUES(null," +
                order.getUserId() + ",'" +
                order.getAddress() + "','" +
                order.getDateOfOrder() + "'," +
                order.getTotalValue() + ",'" +
                order.getStatus() + "')";
        dbHelper.queryData(query);
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
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getDouble(4),
                    cursor.getString(5)));
        }
        return orderList;
    }
    public Integer quantityOfOrder(){
        String query = "SELECT COUNT(*) FROM gioHang WHERE status='Delivered'";
        Cursor cursor = dbHelper.getDataRow(query);
        return cursor.getInt(0);
    }
}