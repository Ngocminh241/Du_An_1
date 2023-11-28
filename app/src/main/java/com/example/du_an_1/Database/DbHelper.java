package com.example.du_an_1.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

        String createTableLoaiFood = "CREATE TABLE loai_Food(maLoai INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL," +
                "tenLoai TEXT UNIQUE NOT NULL)";

        String createTableFood = "create table FOOD(maFood TEXT PRIMARY KEY UNIQUE NOT NULL," +
                "maLoai INTEGER REFERENCES loai_Food(maLoai),"+
                "tenFood TEXT NOT NULL," +
                "giaFood INTEGER NOT NULL," +
                "hinhAnh BLOB,"+
                "moTa TEXT NOT NULL)";


        String addAdmin = "INSERT INTO User(maDN,matKhau,hoTen,sDT,vaiTro) VALUES('admin','admin','Admin','admin',1)";
        String addLoai = "INSERT INTO loai_Food(maLoai, tenLoai) VALUES(0,'Pizza'),"+"(1,'Burger'),"+"(2,'Hotdog'),"+"(3,'Drink'),"+"(4,'Donut')";
        String addPizza = "INSERT INTO FOOD(maFood,maLoai,tenFood,giaFood,hinhAnh,moTa) VALUES('F001',1,'Pizza Chese',880,null,'abc')";

        db.execSQL(createTableUser);
        db.execSQL(createTableLoaiFood);
        db.execSQL(createTableFood);
        db.execSQL(addAdmin);
        db.execSQL(addLoai);
        db.execSQL(addPizza);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("drop table if exists User");
            db.execSQL("drop table if exists loai_Food");
            db.execSQL("drop table if exists FOOD");
            onCreate(db);
        }
    }

    public static Bitmap convertByteArrayToBitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
