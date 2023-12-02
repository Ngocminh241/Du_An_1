package com.example.du_an_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class DAO_DonHang {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public DAO_DonHang(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long ADDDonHang(GioHang gh) {
        ContentValues values = new ContentValues();
//        values.put("ID_CHI_TIET_DON_HANG", SP.getId_CTDonHang());
//        values.put("ID_DON_HANG", gh.getId_DonHang());
        values.put("NGAY_DAT", gh.getNgayDat());
        values.put("TRANG_THAI_THANH_TOAN", gh.getTrangThai_TT());
        values.put("TONG_GIA", gh.getTong_gia());
        values.put("maUser", gh.getMaUser());
        return db.insert("DON_HANG", null, values);
    }
    public List<GioHang> getAll() {
        List<GioHang> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM DON_HANG INNER JOIN User on DON_HANG.maUser = User.maUser", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int id_gh = c.getInt(0);
                String ngay = c.getString(1);
                int trangThai = c.getInt(2);
                int sum = c.getInt(3);
                int maUser = c.getInt(4);
                list.add(new GioHang(id_gh, ngay, trangThai, sum, maUser));
            } while (c.moveToNext());
        }
        return list;
    }
}
