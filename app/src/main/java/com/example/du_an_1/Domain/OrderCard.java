package com.example.du_an_1.Domain;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;

public class OrderCard extends LinearLayout {
    private GioHang order;

    public OrderCard(Context context){
        super(context);
    }

    public OrderCard(Context context, GioHang order) {
        super(context);
        this.order = order;
        initControl(context);
    }

    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.order_card, this);

        TextView tvDate = findViewById(R.id.tvDateMakeOrder);
        TextView tvPrice = findViewById(R.id.tvOrderPrice);
        TextView tvAddress = findViewById(R.id.tvOrderAddress);
        TextView tvStatus = findViewById(R.id.tvOrderStatus);

        Button btnConfirm = findViewById(R.id.btnConfirmOrder);
        btnConfirm.setOnClickListener(view -> {
            order.setStatus("Delivered");
            MainActivity.dao_gioHang.updateOrder(order);
            Toast.makeText(context, "Đã cập nhật lại trạng thái!", Toast.LENGTH_SHORT).show();
        });
        if(order.getStatus().equals("Delivered")){
            btnConfirm.setEnabled(false);
        }
        if(order.getStatus().equals("Canceled")){
            btnConfirm.setText("ĐÃ HỦY ĐƠN");
            btnConfirm.setEnabled(false);
        }

        tvDate.setText(order.getDateOfOrder());
        tvAddress.setText(order.getAddress());
        tvPrice.setText(getRoundPrice(order.getTotalValue()));
        tvStatus.setText(order.getStatus());
    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }
}
