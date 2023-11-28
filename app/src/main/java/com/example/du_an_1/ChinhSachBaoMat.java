package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChinhSachBaoMat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sach_bao_mat);



        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout settingBtn = findViewById(R.id.setting_btn);
        LinearLayout profile = findViewById(R.id.profile_btn);
        LinearLayout support = findViewById(R.id.support_btn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChinhSachBaoMat.this, Cart_Activity.class));
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChinhSachBaoMat.this, Support.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChinhSachBaoMat.this, Profile.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChinhSachBaoMat.this, MainActivity.class));
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChinhSachBaoMat.this, SettingActivity.class));
            }
        });
    }
}