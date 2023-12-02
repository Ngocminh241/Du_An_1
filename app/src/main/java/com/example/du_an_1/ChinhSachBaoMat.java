package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.User_DAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChinhSachBaoMat extends AppCompatActivity {
    User_DAO user_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sach_bao_mat);


        Intent i = getIntent();
        String user = i.getStringExtra("user");
        user_dao = new User_DAO(this);
        String username = user_dao.getTenTV(user);

        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout settingBtn = findViewById(R.id.setting_btn);
        LinearLayout profile = findViewById(R.id.profile_btn);
        LinearLayout support = findViewById(R.id.support_btn);

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        user_dao = new User_DAO(this);
        String username = user_dao.getTenTV(user);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(ChinhSachBaoMat.this, Cart_Activity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(ChinhSachBaoMat.this, Support.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(ChinhSachBaoMat.this, Profile.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(ChinhSachBaoMat.this, MainActivity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(ChinhSachBaoMat.this, SettingActivity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
    }
}