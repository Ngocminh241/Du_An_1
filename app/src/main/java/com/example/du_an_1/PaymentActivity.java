package com.example.du_an_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.DAO_GioHang;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.DTO.User;
import com.example.du_an_1.DTO.chitietDonHang;

import java.util.ArrayList;
import java.util.List;

public class PaymentActivity extends AppCompatActivity {
    private String name, phone, address, dateOfOrder;
    private Intent intent;
    private static int sum;
    public static User user;
    DAO_chitietDonHang dao_chitietDonHang;
    DAO_GioHang dao_gioHang;
    User_DAO user_dao;
    List<chitietDonHang> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");
        user_dao = new User_DAO(this);
        referencesComponents();
    }
    private void referencesComponents(){
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");
        user_dao = new User_DAO(this);
        dao_gioHang = new DAO_GioHang(this);
        intent = getIntent();
        Integer orderId = intent.getIntExtra("orderId", 0);
        list = new ArrayList<>();

        String username = user_dao.getTenTV(usernameLogged);
        String sdt = user_dao.getsdtTV(usernameLogged);
        TextView tvUser_name = findViewById(R.id.editText_payment_name);
        TextView tvUserPhone = findViewById(R.id.editText_payment_phone);
        TextView tvUserAddress = findViewById(R.id.editText_payment_address);
        TextView tvTotalValue = findViewById(R.id.tv_total_values);

        tvUser_name.setText(username);
        tvUserPhone.setText(sdt);

        // get order
        dao_chitietDonHang = new DAO_chitietDonHang(this);
//        Integer orderId = intent.getIntExtra("orderId", 0);
        list = dao_chitietDonHang.getCartDetailList(orderId);
        Log.i("SQLite", orderId+"");
        sum = 0;
        for(chitietDonHang orderDetail : list){
            sum += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        tvTotalValue.setText(String.valueOf(sum));

        Button btnThanhToan = findViewById(R.id.btnThanhToanThanhToan);
        btnThanhToan.setOnClickListener(view ->
        {
            dao_gioHang = new DAO_GioHang(this);
            name = tvUser_name.getText().toString();
            phone = tvUserPhone.getText().toString();
            address = tvUserAddress.getText().toString();
            if(name.isEmpty() || phone.isEmpty() || address.isEmpty()){
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            dateOfOrder = dao_gioHang.getDate();

            GioHang order = new GioHang(orderId,  Integer.parseInt(user_dao.getMaND(usernameLogged)), address, dateOfOrder,  sum, "Coming");
            dao_gioHang.updateOrder(order);

            Toast.makeText(this, "Đã thanh toán thành công!", Toast.LENGTH_SHORT).show();
            Cart_Activity.cartContainer.removeAllViews();

            // User Notify
//            String content = "Đơn hàng của bạn đang được giao!\nTổng giá trị đơn hàng là " + sum + " VNĐ";
//            dao.addNotify(new Notify(1, "Thông báo về đơn hàng!",
//                    content, dateOfOrder));
//            dao.addNotifyToUser(new NotifyToUser(dao.getNewestNotifyId(), user.getId()));

            finish();
        });

        Button btnCancel = findViewById(R.id.btnThanhToanTroLai);
        btnCancel.setOnClickListener(view -> finish());
    }
}