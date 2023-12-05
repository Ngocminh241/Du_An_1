package com.example.du_an_1;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.DAO_GioHang;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.DTO.chitietDonHang;
import com.example.du_an_1.Domain.CartCard;

import java.util.ArrayList;

public class ViewOrderActivity extends AppCompatActivity {
    private LinearLayout layout_container;
    private TextView tvDate, tvPrice, tvAddress, tvStatus;
    private DAO_GioHang dao_gioHang;
    private DAO_chitietDonHang dao_chitietDonHang;
    private Food_DAO food_dao;
    private GioHang order;
    public static int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        Intent intent = getIntent();
        order = (GioHang) intent.getSerializableExtra("order");

        referencesComponent();
        LoadData();
    }

    public void referencesComponent(){
        layout_container = findViewById(R.id.layout_container_order_detail);

        tvDate = findViewById(R.id.tvDateMakeOrderView);
        tvPrice = findViewById(R.id.tvOrderPriceView);
        tvAddress = findViewById(R.id.tvOrderAddressView);
        tvStatus = findViewById(R.id.tvOrderStatusView);

        Button btnDeleteOrder = findViewById(R.id.btnDeleteOrder);
        if(order.getStatus().equals("Delivered") || order.getStatus().equals("Canceled")){
            btnDeleteOrder.setEnabled(false);
            btnDeleteOrder.setBackgroundColor(Color.GRAY);
        }
        btnDeleteOrder.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Bạn có muốn xóa món đơn hàng này không?");
            dialog.setPositiveButton("Có", (dialogInterface, i) -> {
                order.setStatus("Canceled");
                dao_gioHang.updateOrder(order);
                Toast.makeText(this, "Đơn hàng đã bị hủy!", Toast.LENGTH_SHORT).show();
                finish();
            });
            dialog.setNegativeButton("Không", (dialogInterface, i) -> {});
            dialog.show();
        });

        Button btnCancel = findViewById(R.id.btnCancelOrderView);
        btnCancel.setOnClickListener(view -> finish());
    }

    private void LoadData(){
        tvDate.setText(String.format("Ngày đặt hàng: %s", order.getDateOfOrder()));
        tvAddress.setText(String.format("Địa chỉ: %s", order.getAddress()));
        tvPrice.setText(String.format("Tổng giá trị: %s", getRoundPrice(order.getTotalValue())));
        tvStatus.setText(String.format("Trạng thái giao hàng: %s",order.getStatus()));

        ArrayList<chitietDonHang> orderDetailArrayList = dao_chitietDonHang.getCartDetailList(order.getId());
        if(orderDetailArrayList.size() > 0){;
            for(chitietDonHang orderDetail : orderDetailArrayList){
                Food food = food_dao.getFoodById(orderDetail.getFoodId());
                CartCard card = new CartCard(this, food, orderDetail, false);
                card.setOnClickListener(view -> {
                    ShowDetailActivity_2.food = food;
                    Intent intent = new Intent(this, ShowDetailActivity_2.class);
                    intent.putExtra("food", food);
                    try {
                        startActivity(intent);
                    } catch (Exception e){
                        Toast.makeText(this, "Không thể hiển thị thông tin!", Toast.LENGTH_SHORT).show();
                    }
                });

                layout_container.addView(card);
            }
        }

    }

    private String getRoundPrice(Double price){
        return Math.round(price) + " VNĐ";
    }
}