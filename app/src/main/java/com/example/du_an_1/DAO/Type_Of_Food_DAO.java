package com.example.du_an_1.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class Type_Of_Food_DAO {
    private SQLiteDatabase db;

    public Type_Of_Food_DAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long ADDSanPham(Type_Of_Food LSP) {
        ContentValues values = new ContentValues();
        values.put("maLoai", LSP.getMaLoai());
        values.put("tenLoai", LSP.getTenLoai());
        values.put("anh", LSP.getHinhAnh());
        return db.insert("loai_Food", null, values);
    }

    //xóa mềm
    public int KhoiphucRow(Type_Of_Food LSP) {
        ContentValues values = new ContentValues();
        values.put("tinhTrang", 0);

        String[] index = new String[]{
                String.valueOf(LSP.getMaLoai())

        };
        return db.update("loai_Food", values, "maLoai=?", index);
    }
    public int DeleteRow(Type_Of_Food LSP) {
        ContentValues values = new ContentValues();
        values.put("tinhTrang", 1);

        String[] index = new String[]{
                String.valueOf(LSP.getMaLoai())

        };
        return db.update("loai_Food", values, "maLoai=?", index);
    }
    public int Deletevinhvien(Type_Of_Food LSP) {
        String[] index = new String[]{
                String.valueOf(LSP.getMaLoai())
        };
        return db.delete("loai_Food", "maLoai=?", index);
    }

    public int Update(Type_Of_Food LSP) {
        ContentValues values = new ContentValues();
        values.put("maLoai", LSP.getMaLoai());
        values.put("tenLoai", LSP.getTenLoai());
        values.put("anh", LSP.getHinhAnh());
        String[] index = new String[]{
                String.valueOf(LSP.getMaLoai())
        };
        return db.update("loai_Food", values, "maLoai=?", index);
    }
    public List<Type_Of_Food> getAll() {
        String sql = "SELECT * FROM loai_Food";
        return getData(sql);
    }

    public List<Type_Of_Food> getAllTY(int tinhTrang) {
        List<Type_Of_Food> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM loai_Food where tinhTrang =" + tinhTrang, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                int maLoai = c.getInt(0);
//                int maLoai = c.getInt(1);
                String tenLoai = c.getString(1);
//                int giaFood = c.getInt(3);
                byte[] anh = c.getBlob(2);
//                String moTa = c.getString(5);
                int trangthai = c.getInt(3);
                list.add(new Type_Of_Food(maLoai, tenLoai, anh, trangthai));
            } while (c.moveToNext());
        }
        return list;
    }
    int theLoai;
    public String getTheLoai(String maTV) {
        try {
            Cursor cursor = db.rawQuery("SELECT maLoai FROM loai_Food WHERE tenLoai=?", new String[] {String.valueOf(maTV)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    theLoai = Integer.parseInt(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return String.valueOf(theLoai);
    }
    int trang;
    public String getTrangThai(int maTV) {
        try {
            Cursor cursor = db.rawQuery("SELECT tinhTrang FROM loai_Food WHERE maLoai=?", new String[] {String.valueOf(maTV)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    trang = Integer.parseInt(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return String.valueOf(trang);
    }

    public Type_Of_Food getID(String id) {
        String sql = "SELECT * FROM loai_Food WHERE maLoai=?";
        List<Type_Of_Food> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Type_Of_Food> getData(String sql, String... selectionArgs) {
        List<Type_Of_Food> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Type_Of_Food obj = new Type_Of_Food();
            obj.setMaLoai(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }
}
