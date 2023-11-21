package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    public List<Type_Of_Food> getAll() {
        String sql = "SELECT * FROM loai_Food";
        return getData(sql);
    }

    public Type_Of_Food getID(String id) {
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<Type_Of_Food> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Type_Of_Food> getData(String sql, String... selectionArgs) {
        List<Type_Of_Food> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Type_Of_Food obj = new Type_Of_Food();
            obj.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maLoai"))));
            obj.setTenLoai(cursor.getString(cursor.getColumnIndex("tenLoai")));
            list.add(obj);
        }
        return list;
    }
}
