package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.User;
import com.google.android.material.textfield.TextInputEditText;

public class Change_Pass extends AppCompatActivity {
    TextInputEditText edPassOld, edPassChange, edRePassChange;
    Button btnSaveUserChange, btnCancleUserChange;
    private User_DAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);

        //anh xa
        userDao = new User_DAO(Change_Pass.this);
        edPassOld = findViewById(R.id.edPassOld);
        edPassChange = findViewById(R.id.edPassChange);
        edRePassChange = findViewById(R.id.edRePassChange);
        btnSaveUserChange = findViewById(R.id.btnSaveUserChange);
        btnCancleUserChange  = findViewById(R.id.btnCancelUserChange);
        btnCancleUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPassChange.setText("");
                edRePassChange.setText("");
            }
        });
        btnSaveUserChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if (validate() > 0) {
                    User user1 = userDao.getMaDN(user);
                    user1.setMatKhau(edPassChange.getText().toString());

                    if (userDao.updatePass(user1) > 0) {
                        Toast.makeText(Change_Pass.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPassChange.setText("");
                        edRePassChange.setText("");
                    } else {
                        Toast.makeText(Change_Pass.this, "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    public int validate(){
        int check =1;
        if (edPassOld.getText().length() == 0 || edPassChange.getText().length() == 0 || edRePassChange.getText().length() == 0){
            Toast.makeText(this, "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD","");
            String pass = edPassChange.getText().toString();
            String rePass = edRePassChange.getText().toString();
            if (!passOld.equals(edPassOld.getText().toString())){
                Toast.makeText(this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}