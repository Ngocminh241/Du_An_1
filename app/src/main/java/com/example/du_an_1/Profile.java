package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.User_DAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {
    User_DAO user_dao;
    LinearLayout layout_user_information, account_btn_layout_policy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        user_dao = new User_DAO(this);
        String username = user_dao.getTenTV(user);

        account_btn_layout_policy = findViewById(R.id.account_btn_layout_policy);
        account_btn_layout_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, ChinhSachBaoMat.class));
            }
        });
        layout_user_information = findViewById(R.id.layout_user_information);
        TextView tv_name = findViewById(R.id.account_user_name);
        tv_name.setText(username);
        layout_user_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, UserInformationActivity.class));
            }
        });

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
                startActivity(new Intent(Profile.this, Cart_Activity.class));
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Support.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, Profile.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Profile.this, MainActivity.class);
                ic.putExtra("user", user);
                startActivity(ic);
//                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, SettingActivity.class));
            }
        });
    }
}