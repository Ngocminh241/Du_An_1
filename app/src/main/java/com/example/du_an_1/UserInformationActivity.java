package com.example.du_an_1;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.User;

import java.util.List;

public class UserInformationActivity extends AppCompatActivity {
    User_DAO user_dao;
    List<User> list;
    String ten, mk, madn, sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        user_dao = new User_DAO(this);
        String username = user_dao.getTenTV(user);
        int maND = Integer.parseInt(user_dao.getMaND(user));

        EditText editText_user_name = findViewById(R.id.editText_user_name);
        EditText editText_user_phone = findViewById(R.id.editText_user_phone);
        EditText editMaDN = findViewById(R.id.editMaDN);
        EditText editMK = findViewById(R.id.editMK);

        Button btnChangeUserInformation = findViewById(R.id.btnChangeUserInformation);
        Button btnCancelChangeUserInformation = findViewById(R.id.btnCancelChangeUserInformation);

        editText_user_name.setText(user_dao.getTenTV(user));
        editText_user_phone.setText(user_dao.getsdtTV(user));
        editMaDN.setText(user);
        editMK.setText(user_dao.getmkTV(user));
        Log.e(TAG, "update: "+editText_user_name.getText().toString());
        Log.e(TAG, "update: "+ user_dao.getMaND(user));
        madn = user;
        btnCancelChangeUserInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(UserInformationActivity.this, Profile.class);
                ic.putExtra("user", madn);
                startActivity(ic);
            }
        });
        btnChangeUserInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User Us = new User();
                if (!editText_user_name.getText().toString().isEmpty() && !editText_user_phone.getText().toString().isEmpty() && !editMaDN.getText().toString().isEmpty() && !editMK.getText().toString().isEmpty()) {
                    Us.setHoTen(editText_user_name.getText().toString());
                    Us.setsDT(editText_user_phone.getText().toString());
                    Us.setMaDN(editMaDN.getText().toString());
                    Us.setMatKhau(editMK.getText().toString());

                    if ((user_dao.Update(Us) > 0)) {
                        Toast.makeText(UserInformationActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        editText_user_name.setText(Us.getHoTen());
                        editText_user_phone.setText(Us.getsDT());
                        editMaDN.setText(Us.getMaDN());
                        editMK.setText(Us.getMatKhau());
                    } else {
                        Toast.makeText(UserInformationActivity.this, "Sửa  thất bại! Vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserInformationActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}