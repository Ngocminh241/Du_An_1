package com.example.du_an_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.User_DAO;

public class Login extends AppCompatActivity {

    TextView tv_create_new_acc;
    Button btn_login;
    EditText ed_username_input_login, ed_password_input_login;
    CheckBox chkRememberPass;
    User_DAO user_dao;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_create_new_acc = findViewById(R.id.tv_create_new_acc);
        btn_login = findViewById(R.id.btn_login);
        ed_username_input_login = findViewById(R.id.ed_username_input_login);
        ed_password_input_login = findViewById(R.id.ed_password_input_login);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        user_dao = new User_DAO(this);
        //
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);

        ed_username_input_login.setText(user);
        ed_password_input_login.setText(pass);
        chkRememberPass.setChecked(rem);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        tv_create_new_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Login.this, Create_acc.class);
                        startActivity(intent);
                    }
                }, 1200);
            }
        });
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            // xoa trang thai luu truoc do
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        // luu lai toan bo du lieu
        edit.commit();
    }

    public void checkLogin() {
        strUser = ed_username_input_login.getText().toString();
        strPass = ed_password_input_login.getText().toString();
        if (strUser.trim().isEmpty() || strPass.trim().isEmpty()) {
            Toast.makeText(this, "Username or Password cannot be left blank", Toast.LENGTH_SHORT).show();
        } else {
            if (user_dao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, chkRememberPass.isChecked());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
