package com.example.du_an_1.Domain;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Cart_Activity;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.chitietDonHang;
import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;

public class CartCard extends LinearLayout{
    private Food food;
    private String restaurantName;
    private chitietDonHang card;
    private boolean activateControl;
    private int quantity;
    private ImageView img_picFood;

    public CartCard(Context context) {
        super(context);
        initControl(context);
    }

    public CartCard(Context context, Food food, chitietDonHang card) {
        super(context);
        this.food = food;
        this.card = card;
        this.activateControl = true;
        initControl(context);
    }

    public CartCard(Context context, Food food, chitietDonHang card, boolean activateControl) {
        super(context);
        this.food = food;
        this.card = card;
        this.activateControl = activateControl;
        initControl(context);
    }

    @SuppressLint("SetTextI18n")
    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cart_card, this);
        quantity = card.getQuantity();

        ImageView image = findViewById(R.id.imageCartFood);
        TextView tvName = findViewById(R.id.tvFoodNameCart);
        TextView tvPrice = findViewById(R.id.tvFoodPriceCart);

        // For quantity
        TextView tvQuantity = findViewById(R.id.tvFoodQuantity_Cart);
        Button btnSub = findViewById(R.id.btnSubQuantity_Cart);
        btnSub.setOnClickListener(view -> {
            if(quantity > 1){
                quantity--;
                tvQuantity.setText("" + quantity);
                card.setQuantity(quantity);
                MainActivity.dao_chitietDonHang.updateQuantity(card);
            }
        });

        Button btnAdd = findViewById(R.id.btnAddQuantity_Cart);
        btnAdd.setOnClickListener(view -> {
            quantity++;
            tvQuantity.setText("" + quantity);
            card.setQuantity(quantity);
            MainActivity.dao_chitietDonHang.updateQuantity(card);
        });

        Button btnDelete = findViewById(R.id.btnDeleteCartItem);
        btnDelete.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("Bạn có muốn xóa món " + food.getTenFood() + " không?");
            dialog.setPositiveButton("Có", (dialogInterface, i) -> {
                if(MainActivity.dao_chitietDonHang.deleteOrderDetailByOrderIdAndFoodId(card.getOrderId(), Integer.valueOf(food.getMaFood()))){
                    Cart_Activity.cartContainer.removeView(this);
                    Toast.makeText(context, "Đã xóa thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Gặp lỗi khi xóa thông tin!", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setNegativeButton("Không", (dialogInterface, i) -> {});
            dialog.show();
        });

        LinearLayout layout = findViewById(R.id.layout_btn_delete);

        if(!activateControl) {
            btnDelete.setVisibility(GONE);
            layout.setBackgroundColor(Color.rgb(255, 70, 70));
            btnAdd.setEnabled(activateControl);
            btnSub.setEnabled(activateControl);
        }

        // Set information for cart card
        image.setImageBitmap(DbHelper.convertByteArrayToBitmap(food.getHinhAnh()));
        tvName.setText(food.getTenFood());
        tvPrice.setText(getRoundPrice(card.getPrice()));
        tvQuantity.setText("" + quantity);
    }

    private String getRoundPrice(int price){
        return Math.round(price) + " VNĐ";
    }
}
