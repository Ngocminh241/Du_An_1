package com.example.du_an_1;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {
    User_DAO user_dao;
    LinearLayout layout_user_information, account_btn_layout_policy;
    String strUser, strPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");
        layout_user_information = findViewById(R.id.layout_user_information);
        TextView tv_name = findViewById(R.id.account_user_name);
        account_btn_layout_policy = findViewById(R.id.account_btn_layout_policy);

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        user_dao = new User_DAO(this);
        String username = user_dao.getTenTV(usernameLogged);
        if (strUser != ""){
            tv_name.setText(username);
        }else if (strUser==""){
            tv_name.setText("Thông tin người dùng");
            dangNhap();
        }


        account_btn_layout_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Profile.this, ChinhSachBaoMat.class);
                ic.putExtra("user",user_dao.getMDNTV(username));
                startActivity(ic);
            }
        });


        layout_user_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangNhap();
            }
        });

        bottomNavigation();



    }
    private void dangNhap() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_dn_profile, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        //ánh xạ
        EditText ed_id = view.findViewById(R.id.txtId_pr);
        EditText sp_pw = view.findViewById(R.id.txtPw_pr);

        Button btn_them = view.findViewById(R.id.btnSaveThem);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUser = ed_id.getText().toString();
                strPass = sp_pw.getText().toString();
                if (strUser.trim().isEmpty() || strPass.trim().isEmpty()) {
                    Toast.makeText(Profile.this, "Username or Password cannot be left blank", Toast.LENGTH_SHORT).show();
                } else {
                    if (user_dao.checkLogin(strUser, strPass) > 0) {
                        for (User obj : user_dao.getAll()) {
                            if (obj.getMaDN().equalsIgnoreCase(strUser) && obj.getMatKhau().equalsIgnoreCase(strPass)) {
                                if (obj.getVaiTro() == 0) {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
//                                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                            Intent ic = new Intent(Profile.this, UserInformationActivity.class);
                                            ic.putExtra("user", strUser);
                                            startActivity(ic);
                                        }
                                    }, 1200);
                                    return;
                                }
                                if (obj.getVaiTro() == 3) {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Tài khoản của bạn đã bị dừng hoạt động. Liên hệ Admin để được hỗ trợ!", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 1200);
                                    return;
                                }
                            }
                        }


                    } else {
                        Toast.makeText(Profile.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        view.findViewById(R.id.btnCancelThem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Profile.this, Support.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Profile.this, Profile.class);
                ic.putExtra("user", user);
                startActivity(ic);
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
                Intent ic = new Intent(Profile.this, SettingActivity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
    }
}