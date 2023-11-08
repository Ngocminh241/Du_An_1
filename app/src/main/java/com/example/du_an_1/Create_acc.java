package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class Create_acc extends AppCompatActivity {
    Button btn_login_acc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);

        btn_login_acc = findViewById(R.id.btn_login_acc);

        btn_login_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Create_acc.this, Login.class);
                        startActivity(intent);
                    }
                }, 1200);
            }
        });
    }
}