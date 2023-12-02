package com.example.du_an_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.User_DAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingActivity extends AppCompatActivity {
    LinearLayout tv_logout, btn_change_pass;
    User_DAO user_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btn_change_pass = findViewById(R.id.btn_change_pass);
        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, Change_Pass.class));
            }
        });
        tv_logout = findViewById(R.id.tv_logout);
        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
        bottomNavigation();

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        user_dao = new User_DAO(this);
        String username = user_dao.getTenTV(user);
    }
    public void Logout(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(SettingActivity.this);
        builder.setTitle("Đăng xuất");
        builder.setMessage("Bạn có muốn đăng xuất không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(SettingActivity.this, Login.class);
                Toast.makeText(SettingActivity.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(SettingActivity.this, "Không đăng xuất", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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
                Intent ic = new Intent(SettingActivity.this, Cart_Activity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(SettingActivity.this, Support.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(SettingActivity.this, Profile.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(SettingActivity.this, MainActivity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(SettingActivity.this, SettingActivity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
    }
}