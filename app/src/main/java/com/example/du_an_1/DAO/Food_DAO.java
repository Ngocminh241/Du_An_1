package com.example.du_an_1.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class Food_DAO {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public Food_DAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long ADDSanPham(Food SP) {
        ContentValues values = new ContentValues();
        values.put("maFood", SP.getMaFood());
        values.put("maLoai", SP.getMaLoai());
        values.put("tenFood", SP.getTenFood());
        values.put("giaFood", SP.getGiaFood());
        values.put("hinhAnh", SP.getHinhAnh());
        values.put("mota", SP.getHinhAnh());
        return db.insert("FOOD", null, values);
    }

    public int DeleteRow(Food SP) {
        String[] index = new String[]{
                String.valueOf(SP.getMaFood())
        };
        return db.delete("FOOD", "maFood=?", index);
    }

    public int Update(Food SP) {
        ContentValues values = new ContentValues();
        values.put("maFood", SP.getMaFood());
        values.put("maLoai", SP.getMaLoai());
        values.put("tenFood", SP.getTenFood());
        values.put("giaFood", SP.getGiaFood());
        values.put("hinhAnh", SP.getHinhAnh());
        values.put("mota", SP.getHinhAnh());
        String[] index = new String[]{
                SP.getMaFood()
        };
        return db.update("FOOD", values, "maFood=?", index);
    }


    public List<Food> getAll() {
        List<Food> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM Food INNER JOIN loai_Food on Food.maLoai = loai_Food.maLoai", null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                String maFood = c.getString(0);
                int maLoai = c.getInt(1);
                String tenFood = c.getString(3);
                int giaFood = c.getInt(4);
                byte[] hinhAnh = c.getBlob(5);
                String moTa = c.getString(6);
                list.add(new Food(maFood, maLoai, tenFood, giaFood, hinhAnh, moTa));
            } while (c.moveToNext());
        }
        return list;
    }
//    public List<DTO_sp> getAllA() {
//        List<DTO_sp> list = new ArrayList<>();
//        Cursor c = db.rawQuery("SELECT * FROM tb_SanPham INNER JOIN tb_loaihang on tb_SanPham.MaLoai = tb_loaihang.MaLoai", null);
//        if (c != null && c.getCount() > 0) {
//            c.moveToFirst();
//            do {
//                String MaSP = c.getString(0);
//                int MaLoai = c.getInt(1);
//                int MaND = c.getInt(2);
//                String TenSP = c.getString(3);
//                byte[] anh = c.getBlob(4);
//                int trangtha = c.getInt(6);
//                String tenLoai = c.getString(8);
//                list.add(new DTO_sp(MaSP, MaLoai, tenLoai, MaND, TenSP, anh, trangtha));
//            } while (c.moveToNext());
//        }
//        return list;
//    }

}
