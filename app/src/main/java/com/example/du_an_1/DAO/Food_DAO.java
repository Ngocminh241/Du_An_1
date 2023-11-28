package com.example.du_an_1.DAO;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.Database.DbHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Food_DAO {
    DbHelper dbHelper;
    SQLiteDatabase db;

    Food food;

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
        values.put("mota", SP.getMoTa());
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
        values.put("mota", SP.getMoTa());
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
                String tenFood = c.getString(2);
                int giaFood = c.getInt(3);
                byte[] hinhAnh = c.getBlob(4);
                String moTa = c.getString(5);
                list.add(new Food(maFood, maLoai, tenFood, giaFood, hinhAnh, moTa));
            } while (c.moveToNext());
        }
        return list;
    }

//    public Food getID(int id) {
//        String sql = "SELECT * FROM User WHERE maUser=?";
//        List<Food> list = getAll(sql, String.valueOf(id));
//        return list.get(0);
//    }
    String tenFD;
    public String getTenFood(String tenFood) {
        try {
            Cursor cursor = db.rawQuery("SELECT tenFood FROM FOOD WHERE maFood=?", new String[] {String.valueOf(tenFood)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    tenFD = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return tenFD;
    }

    String mT;
    public String getMOTA(String MT) {
        try {
            Cursor cursor = db.rawQuery("SELECT moTa FROM FOOD WHERE maFood=?", new String[] {String.valueOf(MT)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    mT = cursor.getString(0);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return mT;
    }

    int giaFOOD;
    public String getGIA(String gia) {
        try {
            Cursor cursor = db.rawQuery("SELECT giaFood FROM FOOD WHERE maFood=?", new String[] {String.valueOf(gia)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    giaFOOD = Integer.parseInt(cursor.getString(0));
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return String.valueOf(giaFOOD);
    }

    byte[] hinh;
    public byte[] getAnh(String HA) {
        try {
            Cursor cursor = db.rawQuery("SELECT hinhAnh FROM FOOD WHERE maFood=?", new String[] {String.valueOf(HA)});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    hinh = String.valueOf((cursor.getBlob(0))).getBytes();
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Lỗi" + e);
        }
        return hinh;
    }

    public Uri hienthi(Context context) {
        byte[] imageData = food.getHinhAnh();// Mảng byte chứa dữ liệu hình ảnh
        String tempFileName = "temp_image.jpg";
        Uri uri;
        // Tạo đường dẫn tới tập tin ảnh tạm
        File tempFile = new File(context.getCacheDir(), tempFileName);
        // Ghi dữ liệu blob vào tập tin ảnh tạm
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            fileOutputStream.write(imageData);
            fileOutputStream.close();
            uri = Uri.fromFile(tempFile);
            return uri;
        } catch (Exception e) {
            return null;
        }
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
