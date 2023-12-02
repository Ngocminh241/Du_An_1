package com.example.du_an_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public long ADDSanPham(chitietDonHang SP) {
        ContentValues values = new ContentValues();
//        values.put("ID_CHI_TIET_DON_HANG", SP.getId_CTDonHang());
        values.put("ID_DON_HANG", SP.getId_DonHang());
        values.put("maFood", SP.getMaFood());
        values.put("SO_LUONG", SP.getSoLuong());
        values.put("GIA", SP.getGia());
        return db.insert("FOOD", null, values);
    }
    public List<chitietDonHang> getAll() {
        List<chitietDonHang> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM CHI_TIET_DON_HANG JOIN FOOD on CHI_TIET_DON_HANG.maFood = FOOD.maFood " +
                "JOIN DON_HANG on CHI_TIET_DON_HANG.ID_DON_HANG = DON_HANG.ID_DON_HANG", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id_ctdh = c.getInt(0);
                int id_dh = c.getInt(1);
                String id_food = c.getString(2);
                int so_luong = c.getInt(3);
                int gia = c.getInt(4);
                list.add(new chitietDonHang(id_ctdh, id_dh, id_food, so_luong, gia));
            } while (c.moveToNext());
        }
        return list;
    }
}
