package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.DTO.Pizza;
import com.example.du_an_1.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class Pizza_DAO {
    DbHelper dbHelper;
    SQLiteDatabase db;

    public Pizza_DAO(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    private List<Pizza> getData(String sql, String... selectionArgs) {
        List<Pizza> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Pizza obj = new Pizza();
            obj.setMaPizza(cursor.getInt(cursor.getColumnIndex("maPizza")));
            obj.setTenPizza(cursor.getString(cursor.getColumnIndex("tenPizza")));
            obj.setGiaPizza(cursor.getInt(cursor.getColumnIndex("giaPizza")));
            obj.setHinhAnh(cursor.getBlob(cursor.getColumnIndex("hinhAnh")));

            list.add(obj);
        }
        return list;
    }
}
