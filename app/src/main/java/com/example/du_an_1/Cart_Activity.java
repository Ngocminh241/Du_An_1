package com.example.du_an_1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.du_an_1.DAO.DAO_GioHang;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.DTO.chitietDonHang;
import com.example.du_an_1.Domain.CartCard;
import com.example.du_an_1.Domain.OrderCard;

import java.util.ArrayList;
import java.util.Objects;

public class Cart_Activity extends AppCompatActivity {
    public static LinearLayout cartContainer;
    private static String status;
    private LinearLayout btnDangDen, btnLichSu, btnGioHang;
    private TextView tvGioHang, tvDangDen, tvLichSu;
    User_DAO user_dao ;
    DAO_GioHang dao_gioHang;
    DAO_chitietDonHang dao_chitietDonHang;
    Food_DAO food_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartContainer = findViewById(R.id.cartContainer);

        referencesComponent();
        LoadOrder("craft");
        status = "craft";
    }

    private void referencesComponent(){
        btnGioHang = findViewById(R.id.btnGioHang);
        btnGioHang.setOnClickListener(view ->{
            resetAttribute();
            btnGioHang.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(this),R.color.main_color));
            tvGioHang.setTextColor(Color.WHITE);

            LoadOrder("craft");
        });

        btnDangDen = findViewById(R.id.btnDangDen);
        btnDangDen.setOnClickListener(view->{
            resetAttribute();
            btnDangDen.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(this),R.color.main_color));
            tvDangDen.setTextColor(Color.WHITE);

            LoadOrder("coming");
        });

        btnLichSu = findViewById(R.id.btnLichSu);
        btnLichSu.setOnClickListener(view -> {
            resetAttribute();
            btnLichSu.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(this),R.color.main_color));
            tvLichSu.setTextColor(Color.WHITE);

            LoadOrder("history");
        });

        tvGioHang = findViewById(R.id.tvGioHang);
        tvDangDen = findViewById(R.id.tvDangDen);
        tvLichSu = findViewById(R.id.tvLichSu);

        Button btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnClickListener(view ->{
            if(!status.equals("craft"))
                return;

            Cursor cursor = dao_gioHang.getCart(MainActivity.user.getMaUser());
            if(!cursor.moveToFirst())
                return;

            PaymentActivity.user = MainActivity.user;
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra("orderId", cursor.getInt(0));
            startActivity(intent);
        });
    }

    private void LoadOrder(String type){
        user_dao = new User_DAO(this);
        Intent i = getIntent();
        String user_1 = i.getStringExtra("user");
        int mangdung = Integer.valueOf(user_dao.getMaND(user_1));
        status = type;
        cartContainer.removeAllViews();
        switch (type) {
            case "craft":
                dao_gioHang = new DAO_GioHang(this);
                Cursor cursor = dao_gioHang.getCart(mangdung);
                if (!cursor.moveToFirst())
                    return;
                cursor.moveToFirst();
                ArrayList<chitietDonHang> orderDetailArrayList = dao_chitietDonHang.getCartDetailList(cursor.getInt(0));
                if (orderDetailArrayList.size() > 0) {
                    Food food;
                    for (chitietDonHang orderDetail : orderDetailArrayList) {
                        food = food_dao.getFoodById(orderDetail.getFoodId());
                        CartCard card = new CartCard(this, food, orderDetail);
                        cartContainer.addView(card);
                    }
                }
                break;
            case "coming": {
                ArrayList<GioHang> orderArrayList = dao_gioHang.getOrderOfUser(Integer.valueOf(user_dao.getMaND(user_1)), "Coming");
                if (orderArrayList.size() > 0) {
                    for (GioHang order : orderArrayList) {
                        OrderCard card = new OrderCard(this, order);
                        card.setOnClickListener(view -> {
                            Intent intent = new Intent(this, ViewOrderActivity.class);
                            intent.putExtra("order", order);
                            startActivity(intent);
                        });
                        cartContainer.addView(card);
                    }
                }
                break;
            }
            case "history": {
                ArrayList<GioHang> orderArrayList = dao_gioHang.getOrderOfUser(Integer.valueOf(user_dao.getMaND(user_1)), "Delivered");
                if (orderArrayList.size() > 0) {
                    for (GioHang order : orderArrayList) {
                        OrderCard card = new OrderCard(this, order);
                        card.setOnClickListener(view -> {
                            Intent intent = new Intent(this, ViewOrderActivity.class);
                            intent.putExtra("order", order);
                            startActivity(intent);
                        });
                        cartContainer.addView(card);
                    }
                }
                break;
            }
        }
    }
    private void resetAttribute(){
        btnGioHang.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(this),R.drawable.bg_white));
        btnDangDen.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_white));
        btnLichSu.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_white));

        tvGioHang.setTextColor(Color.BLACK);
        tvLichSu.setTextColor(Color.BLACK);
        tvDangDen.setTextColor(Color.BLACK);
    }
}