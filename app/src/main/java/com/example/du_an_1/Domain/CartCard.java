package com.example.du_an_1.Domain;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.Cart_Activity;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.chitietDonHang;
import com.example.du_an_1.R;

public class CartCard extends LinearLayout{
    private Food food;
    private String restaurantName;
    private chitietDonHang card;
    private boolean activateControl;
    private int quantity;
    private ImageView img_picFood;
    String tenfood;
    Food_DAO food_dao ;
    DAO_chitietDonHang dao_chitietDonHang;
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
        int ma = card.getOrderId();
        quantity = card.getQuantity();
        dao_chitietDonHang = new DAO_chitietDonHang(context);
        String mafod = dao_chitietDonHang.getIdmF(ma);
        food_dao = new Food_DAO(context);
        Log.i("SQLite", "Ma: "+ma);

        ImageView image = findViewById(R.id.imageCartFood);
        TextView tvName = findViewById(R.id.tvFoodNameCart);
        TextView tvPrice = findViewById(R.id.tvFoodPriceCart);
        tenfood = String.valueOf(tvName);

                // For quantity
        TextView tvQuantity = findViewById(R.id.tvFoodQuantity_Cart);
        Button btnSub = findViewById(R.id.btnSubQuantity_Cart);
        btnSub.setOnClickListener(view -> {
            if(quantity > 1){
                dao_chitietDonHang = new DAO_chitietDonHang(context);
                quantity--;
                tvQuantity.setText("" + quantity);
                card.setQuantity(quantity);
                dao_chitietDonHang.updateQuantity(card);
            }
        });

        Button btnAdd = findViewById(R.id.btnAddQuantity_Cart);
        btnAdd.setOnClickListener(view -> {
            dao_chitietDonHang = new DAO_chitietDonHang(context);
            quantity++;
            tvQuantity.setText("" + quantity);
            card.setQuantity(quantity);
            dao_chitietDonHang.updateQuantity(card);

        });

        Button btnDelete = findViewById(R.id.btnDeleteCartItem);
        btnDelete.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("Bạn có muốn xóa món " + tenfood + " không?");
            dialog.setPositiveButton("Có", (dialogInterface, i) -> {
                dao_chitietDonHang = new DAO_chitietDonHang(getContext());
                if(dao_chitietDonHang.deleteOrderDetailByOrderIdAndFoodId(ma)){
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



//        byte[] imgdata = food.convertByteArrayToBitmap(food.getHinhAnh());
//        Bitmap bitmap = BitmapFactory.decodeByteArray(imgdata, 0, imgdata.length);
//        image.setImageBitmap(bitmap);
//        image.setImageBitmap(food.convertByteArrayToBitmap());
        tvName.setText(food_dao.getTenFood(card.getFoodId()));
        tvPrice.setText(getRoundPrice(card.getPrice()));
        tvQuantity.setText("" + quantity);
    }

    private String getRoundPrice(int price){
        return Math.round(price) + " VNĐ";
    }
}
