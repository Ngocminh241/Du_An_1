package com.example.du_an_1.Database;

import android.content.Context;
import android.database.Cursor;
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
    public Cursor getDataRow(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        return c;
    }
    public void queryData(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public Cursor getData(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);
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

        String createTableLoaiFood = "CREATE TABLE loai_Food(maLoai INTEGER PRIMARY KEY UNIQUE NOT NULL," +
                "tenLoai TEXT UNIQUE NOT NULL,"+
                "anh BLOB,"+
                "tinhTrang INTEGER DEFAULT (0))";

        String createTableFood = "create table FOOD(maFood TEXT PRIMARY KEY UNIQUE NOT NULL," +
                "maLoai INTEGER REFERENCES loai_Food(maLoai),"+
                "tenFood TEXT NOT NULL," +
                "giaFood INTEGER NOT NULL," +
                "hinhAnh BLOB,"+
                "moTa TEXT NOT NULL," +
                "trangThai INTEGER DEFAULT (0))";

        String createTablegioHang = "create table gioHang(" +
                        "ID_DON_HANG INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "id_ctdh INTEGER REFERENCES CHI_TIET_DON_HANG(id_ctdh),"+
                        "maUser INTEGER REFERENCES User(maUser),"+
                        "address TEXT NOT NULL," +
                        "NGAY_DAT TEXT NOT NULL," +
                        "TONG_GIA INTEGER NOT NULL," +
                        "status TEXT)";

        String createTableChiTietDonHang =
                "CREATE TABLE IF NOT EXISTS CHI_TIET_DON_HANG(" +
                        "id_ctdh INTEGER," +
                        "maFood TEXT REFERENCES FOOD(maFood)," +
                        "GIA INTEGER NOT NULL," +
                        "SO_LUONG INTEGER NOT NULL," +
                        "PRIMARY KEY (id_ctdh, maFood ))";

        String addAdmin = "INSERT INTO User(maDN,matKhau,hoTen,sDT,vaiTro) VALUES('admin','admin','Admin','admin',1)";
        String addLoai = "INSERT INTO loai_Food(maLoai, tenLoai, anh,tinhTrang) VALUES(0,'Pizza',null,0),"+"(1,'Burger',null,0),"+"(2,'Hotdog',null,0),"+"(3,'Drink',null,0),"+"(4,'Donut',null,0)";
        String addPizza = "INSERT INTO FOOD(maFood,maLoai,tenFood,giaFood,hinhAnh,moTa,trangThai) VALUES('F001',0,'Pizza Chese',880,null,'abc',0)";

        db.execSQL(createTableUser);
        db.execSQL(createTableLoaiFood);
        db.execSQL(createTableFood);
        db.execSQL(createTablegioHang);
        db.execSQL(createTableChiTietDonHang);
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
            db.execSQL("drop table if exists DON_HANG");
            db.execSQL("drop table if exists CHI_TIET_DON_HANG");
            onCreate(db);
        }
    }

    public static Bitmap convertByteArrayToBitmap(byte[] image){
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }
}
