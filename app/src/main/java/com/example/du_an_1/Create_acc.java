package com.example.du_an_1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.User;

import java.util.ArrayList;
import java.util.List;

public class Create_acc extends AppCompatActivity {
    Button btn_login_acc, btn_create_acc;
    EditText ed_username_input_create, ed_sdt_input_create, ed_password_input_create, ed_re_password_input_create, ed_userFullname_input_create;
    Context context;
    String fullName, phoneNumber, userName, passWord, confirm;
    User user;
    User_DAO dao_user;
    Dialog dialog;
    List<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);
        ed_userFullname_input_create = findViewById(R.id.ed_userFullname_input_create);
        ed_username_input_create = findViewById(R.id.ed_username_input_create);
        ed_sdt_input_create = findViewById(R.id.ed_sdt_input_create);
        ed_password_input_create = findViewById(R.id.ed_password_input_create);
        ed_re_password_input_create = findViewById(R.id.ed_re_password_input_create);

        btn_create_acc = findViewById(R.id.btn_create_acc);
        btn_login_acc = findViewById(R.id.btn_login_acc);

        taoDoiTuong();

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
        btn_create_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog_tb();
            }
        });
    }
    public void openDialog_tb() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Create_acc.this);
        builder.setTitle("Save");
        builder.setMessage("Bạn có chắc chắn muốn Save không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fullName = ed_userFullname_input_create.getText().toString().trim();
                phoneNumber = ed_sdt_input_create.getText().toString().trim();
                userName = ed_username_input_create.getText().toString().trim();
                passWord = ed_password_input_create.getText().toString().trim();
                confirm = ed_re_password_input_create.getText().toString().trim();
                user = new User();
                user.setHoTen(fullName);
                user.setsDT(phoneNumber);
                user.setMaDN(userName);
                user.setMatKhau(passWord);
                user.setVaiTro(0);
//                dao_user.insert(user);
//                dialog.dismiss();
//                Intent intent = new Intent(Create_acc.this, Login.class);
//                startActivity(intent);
//                Toast.makeText(Create_acc.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                validate();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void taoDoiTuong(){
        dialog = new Dialog(Create_acc.this);
        dao_user =new User_DAO(dialog.getContext());
        list=new ArrayList<>();
    }
    public int validate() {
        int check = 1;
        if (ed_userFullname_input_create.getText().length() == 0 || ed_sdt_input_create.getText().length() == 0 || ed_username_input_create.getText().length() == 0 || ed_password_input_create.getText().length() == 0 || ed_re_password_input_create.getText().length() == 0) {
            Toast.makeText(Create_acc.this, "Bạn phải nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            check = -1;
        }else if(!confirm.equals(passWord)){
            Toast.makeText(Create_acc.this, "Mật khẩu xác nhận không hợp lệ!", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            dao_user.insert(user);
            dialog.dismiss();
            Intent intent = new Intent(Create_acc.this, Login.class);
            startActivity(intent);
            Toast.makeText(Create_acc.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }
        return check;
    }
}