package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.DAO_GioHang;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.DTO.User;
import com.example.du_an_1.DTO.chitietDonHang;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    private String name, phone, address, dateOfOrder;
    private Intent intent;
    private static double sum;
    public static User user;
    DAO_chitietDonHang dao_chitietDonHang;
    DAO_GioHang dao_gioHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        intent = getIntent();

        referencesComponents();
    }
    private void referencesComponents(){
        TextView tvUser_name = findViewById(R.id.editText_payment_name);
        TextView tvUserPhone = findViewById(R.id.editText_payment_phone);
        TextView tvUserAddress = findViewById(R.id.editText_payment_address);
        TextView tvTotalValue = findViewById(R.id.tv_total_values);

        tvUser_name.setText(MainActivity.user.getHoTen());
        tvUserPhone.setText(MainActivity.user.getsDT());

        // get order
        Integer orderId = intent.getIntExtra("orderId", 0);
        ArrayList<chitietDonHang> orderDetailArrayList = dao_chitietDonHang.getCartDetailList(orderId);
        sum = 0;
        for(chitietDonHang orderDetail : orderDetailArrayList){
            sum += orderDetail.getPrice() * orderDetail.getQuantity();
        }
        tvTotalValue.setText(String.format("%s VNĐ", sum));

        Button btnThanhToan = findViewById(R.id.btnThanhToanThanhToan);
        btnThanhToan.setOnClickListener(view ->
        {
            name = tvUser_name.getText().toString();
            phone = tvUserPhone.getText().toString();
            address = tvUserAddress.getText().toString();
            if(name.isEmpty() || phone.isEmpty() || address.isEmpty()){
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            dateOfOrder = dao_gioHang.getDate();

            GioHang order = new GioHang(orderId, user.getMaUser(), address, dateOfOrder,  sum, "Coming");
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