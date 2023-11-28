package com.example.du_an_1.DAO;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        values.put("maDN", obj.getMaDN());
        values.put("matKhau", obj.getMatKhau());
        values.put("hoTen", obj.getHoTen());
        values.put("sDT", obj.getsDT());
        values.put("vaiTro", obj.getVaiTro());
        return db.insert("User", null, values);
    }

    public long updatePass(User obj) {
        ContentValues values = new ContentValues();
        values.put("maDN", obj.getMaDN());
        values.put("matKhau", obj.getMatKhau());
        return db.update("User", values, "maDN = ?", new String[]{String.valueOf(obj.getMaDN())});
    }

    public long delete(String id) {
        return db.delete("User", "maUser = ?", new String[]{String.valueOf(id)});
    }

    public List<User> getAll() {
        String sql = "SELECT * FROM User";
        return getData(sql);
    }

    public User getID(int id) {
        String sql = "SELECT * FROM User WHERE maUser=?";
        List<User> list = getData(sql, String.valueOf(id));
        return list.get(0);
    }
    public User getMaDN(String id) {
        String sql = "SELECT * FROM User WHERE maDN=?";
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
            obj.setMaUser(cursor.getInt(cursor.getColumnIndex("vaiTro")));
            list.add(obj);
        }
        return list;
    }



    String tenTV;
    public String getTenTV(String maTV) {
        try {
            Cursor cursor = db.rawQuery("SELECT hoTen FROM User WHERE maDN=?", new String[] {String.valueOf(maTV)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tenTV = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return tenTV;
    }


}
