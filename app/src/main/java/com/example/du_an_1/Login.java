package com.example.du_an_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.User;

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
        tv_create_new_acc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    tv_create_new_acc.setTextColor(Color.RED);
                }
                if (motionEvent.getAction()== MotionEvent.ACTION_UP){
                    tv_create_new_acc.setTextColor(Color.BLUE);
                }
                return false;
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
                rememberUser(strUser, strPass, chkRememberPass.isChecked());
                for (User obj : user_dao.getAll()) {
                    if (obj.getMaDN().equalsIgnoreCase(strUser) && obj.getMatKhau().equalsIgnoreCase(strPass)) {
                        if (obj.getMaUser() == 1) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent i3 = new Intent(Login.this, NavigationQuanLy.class);
                                    startActivity(i3);
                                }
                            }, 1200);
                            return;
                        }
                        if (obj.getMaUser() != 1) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent ic = new Intent(Login.this, MainActivity.class);
                                    ic.putExtra("user", strUser);
                                    startActivity(ic);
                                    finish();
                                }
                            }, 1200);
                            return;
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
