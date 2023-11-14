package com.example.du_an_1.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.DTO.User;
import com.example.du_an_1.Database.DbHelper;

import java.util.ArrayList;
import java.util.List;

public class User_DAO {
    private SQLiteDatabase db;

    public User_DAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(User obj) {
        ContentValues values = new ContentValues();
        values.put("maUser", obj.getMaUser());
        values.put("maDN", obj.getMaDN());
        values.put("matKhau", obj.getMatKhau());
        values.put("hoTen", obj.getHoTen());
        values.put("sDT", obj.getsDT());
        return db.insert("User", null, values);
    }

    public long updatePass(User obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("User", values, "maUser = ?", new String[]{String.valueOf(obj.getMaUser())});
    }

    public long delete(String id) {
        return db.delete("User", "maUser = ?", new String[]{String.valueOf(id)});
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM User";
        return getData(sql);
    }

    public User getID(String id) {
        String sql = "SELECT * FROM User WHERE maUser=?";
        List<User> list = getData(sql, id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM User WHERE maDN=? AND matKhau=?";
        List<User> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    @SuppressLint("Range")
    private List<User> getData(String sql, String... selectionArgs) {
        List<User> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            User obj = new User();
            obj.setMaUser(cursor.getInt(cursor.getColumnIndex("maUser")));
            obj.setMaDN(cursor.getString(cursor.getColumnIndex("maDN")));
            obj.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            obj.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            obj.setsDT(cursor.getString(cursor.getColumnIndex("sDT")));
            list.add(obj);
        }
        return list;
    }
}
