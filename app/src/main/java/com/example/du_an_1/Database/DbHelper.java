package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "FOODFPOLY";
    public static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUser = "create table User(" +
                "maUser INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maDN TEXT NOT NULL, " +
                "matKhau TEXT NOT NULL,"+
                "hoTen TEXT NOT NULL, " +
                "sDT TEXT NOT NULL," +
                "vaiTro INTEGER NOT NULL)";

        String addAdmin = "INSERT INTO User(maUser,maDN,matKhau,hoTen,sDT,vaiTro) VALUES(1,'admin','admin','Admin','admin',1)";
        db.execSQL(createTableUser);

        db.execSQL(addAdmin);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists User");
            onCreate(db);
        }
    }
}
