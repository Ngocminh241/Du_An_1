package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Support extends AppCompatActivity {
    Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        bottomNavigation();

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogNhanVien();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogNhanVien();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogNhanVien();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaLogNhanVien();
            }
        });

    }
    private void diaLogNhanVien(){
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Support.this);
        builder.setTitle("Thông báo");
        builder.setMessage("Chức năng đang phát triển!");
        builder.setCancelable(true);

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
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


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, Cart_Activity.class));
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, Support.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, Profile.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, MainActivity.class));
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Support.this, SettingActivity.class));
            }
        });
    }
}