package com.example.du_an_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.DAO_GioHang;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.DTO.chitietDonHang;
import com.example.du_an_1.Database.DbHelper;

public class ShowDetailActivity_2 extends AppCompatActivity {
    private ImageView img_picFood;

    private TextView tvName, tvDescription, tvPrice,
            tvQuantity;
    private static int quantity;
    DAO_GioHang dao_gioHang;
    Food_DAO food_dao ;
    private byte[] currentImage;
    User_DAO user_dao;
    String user_1;
    public static int userID;
    public static Food food;
    DAO_chitietDonHang dao_chitietDonHang;
    chitietDonHang chitietDonHang;
    String fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");
        setContentView(R.layout.activity_food_details);
        user_dao = new User_DAO(this);
        dao_chitietDonHang = new DAO_chitietDonHang(this);
        quantity = 1;
        Intent i = getIntent();
        //
        //
        userID = Integer.parseInt(user_dao.getMaND(usernameLogged));
        fd = i.getStringExtra("object_2");
        food_dao = new Food_DAO(this);
        referenceComponent();
        LoadData();
        Log.i("SQLite", userID+"");
    }
    public void setCurrentImage(byte[] currentImage) {
        this.currentImage = currentImage;
    }

    public byte[] getCurrentImage() {
        return currentImage;
    }
    private int getRoundPrice(){
        Intent i = getIntent();
        String fd = i.getStringExtra("object_2");
        food_dao = new Food_DAO(this);
        return Math.round(food_dao.getGIA(fd) * quantity);
    }



    private void referenceComponent() {
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> this.finish());

        tvName = findViewById(R.id.tvFoodName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        img_picFood = findViewById(R.id.image);
        tvQuantity = findViewById(R.id.tvFoodQuantity_Food);

        Button btnAddToCart = findViewById(R.id.btnAddToCart);
        Button btnAddQuantity = findViewById(R.id.btnAddQuantity_Food);
        btnAddQuantity.setOnClickListener(view -> {
            quantity++;
            tvQuantity.setText(String.format("%s", quantity));
            tvPrice.setText(getRoundPrice()+" VND");
        });

        Button btnSubQuantity = findViewById(R.id.btnSubQuantity_Food);
        btnSubQuantity.setOnClickListener(view -> {
            if (quantity > 1) {
                quantity--;
                tvQuantity.setText(String.format("%s", quantity));
                tvPrice.setText(getRoundPrice()+" VND");
            }
        });
        btnAddToCart.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String usernameLogged = sharedPreferences.getString("USERNAME", "");
            userID = Integer.parseInt(user_dao.getMaND(usernameLogged));
            dao_gioHang = new DAO_GioHang(this);
            dao_chitietDonHang = new DAO_chitietDonHang(this);
            Cursor cursor = dao_gioHang.getCart(userID);
            if (!cursor.moveToFirst()){
                dao_gioHang.addOrder(new GioHang(1, 1,userID, "", "", 0, "Craft"));
                cursor = dao_gioHang.getCart(userID);
            }

            // add order detail

            cursor.moveToFirst();
            Intent i = getIntent();
            String fd = i.getStringExtra("object_2");
            food_dao = new Food_DAO(this);
            dao_chitietDonHang = new DAO_chitietDonHang(this);

            chitietDonHang orderDetail = dao_chitietDonHang.getExistOrderDetail(cursor.getInt(0));
//           if ((orderDetail == null)){
                Log.i("SQLite", "tren");
                dao_chitietDonHang = new DAO_chitietDonHang(this);
                food_dao = new Food_DAO(this);
                boolean addOrderDetail = dao_chitietDonHang.addOrderDetail(new chitietDonHang(cursor.getInt(0),
                        fd, food_dao.getGIA(fd), quantity));
                if(addOrderDetail){
                    Toast.makeText(this, "Thêm món ăn vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
                }
                dao_chitietDonHang = new DAO_chitietDonHang(this);
               Log.i("SQLite", "check"+dao_chitietDonHang.getIdmF(Integer.parseInt(dao_chitietDonHang.getIdDH(fd))));
               Log.i("SQLite", "check"+fd);
//           }
//           } else if((orderDetail != null)&&(fd==dao_chitietDonHang.getIdmF(Integer.parseInt(dao_chitietDonHang.getIdDH(fd))))) {
//               if (fd!=dao_chitietDonHang.getIdmF(Integer.parseInt(dao_chitietDonHang.getIdDH(fd)))){
//                   Log.i("SQLite", "Giữa");
//                   dao_chitietDonHang = new DAO_chitietDonHang(this);
//                   food_dao = new Food_DAO(this);
//                   boolean addOrderDetail = dao_chitietDonHang.addOrderDetail(new chitietDonHang(cursor.getInt(0),
//                           fd, food_dao.getGIA(fd), quantity));
//                   if(addOrderDetail){
//                       Toast.makeText(this, "Thêm món ăn vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
//                   } else {
//                       Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
//                   }
//               }else  if (fd==dao_chitietDonHang.getIdmF(Integer.parseInt(dao_chitietDonHang.getIdDH(fd)))) {
//                   Log.i("SQLite", "dưới");
//                   orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
//                   if (dao_chitietDonHang.updateQuantity(orderDetail)) {
//                       Toast.makeText(this, "Thêm món ăn vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
//                   } else {
//                       Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
//                   }
//               }
//            }

        });

    }

    private void LoadData(){
        Intent intent = getIntent();
        if(intent != null){
            Intent i = getIntent();
            String fd = i.getStringExtra("object_2");
//            byte[] anh = i.getByteArrayExtra("image_data");
            food_dao = new Food_DAO(this);

            byte[] imgdata = i.getByteArrayExtra("image_data");
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgdata, 0, imgdata.length);
            img_picFood.setImageBitmap(bitmap);

//            byte[] currentImage = anh;
//            setCurrentImage(currentImage);

            // Set information
            tvName.setText(food_dao.getTenFood(fd));
            tvDescription.setText(food_dao.getMOTA(fd));
            img_picFood.setImageBitmap(DbHelper.convertByteArrayToBitmap(imgdata));
//            img_picFood.setImageBitmap(food.convertByteArrayToBitmap(food_dao.getAnh(fd)));
            tvPrice.setText(getRoundPrice()+"VND");
        }
    }
}