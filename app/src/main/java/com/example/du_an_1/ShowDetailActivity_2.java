package com.example.du_an_1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.Helper.ManagementCart;

import java.util.List;

public class ShowDetailActivity_2 extends AppCompatActivity {
    private TextView tv_addToCart_btn;
    private TextView tv_title_showdetail, tv_price_showdetail, tv_numberOrder_showdetail, tv_description;
    private ImageView img_picFood;
    private ImageView img_minus_btn;
    private ImageView img_plus_btn;
    private TextView tvName, tvDescription, tvPrice,
            tvRestaurantName, tvRestaurantAddress,
            tvPriceSizeS,tvPriceSizeM, tvPriceSizeL,
            tvQuantity;
    private static int quantity;

    Food food;
    List<Food> list;
    private ManagementCart managementCart;
    int numberOrder = 1;
    Food_DAO food_dao ;
    private byte[] currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        quantity = 1;
        managementCart = new ManagementCart(this);

        Intent i = getIntent();
        String fd = i.getStringExtra("object_2");
        food_dao = new Food_DAO(this);
//        initView();
//        getBundle2();
        referenceComponent();
        LoadData();
    }
    public void setCurrentImage(byte[] currentImage) {
        this.currentImage = currentImage;
    }

    public byte[] getCurrentImage() {
        return currentImage;
    }
//    private void getBundle2() {
//        Intent i = getIntent();
//        String fd = i.getStringExtra("object_2");
//        food_dao = new Food_DAO(this);
//        tv_title_showdetail.setText(food_dao.getTenFood(fd));
//        tv_price_showdetail.setText("VND " + food_dao.getGIA(fd));
//        tv_description.setText(food_dao.getMOTA(fd));
//
////        byte[] currentImage = list.get(position).getHinhAnh();
////        byte[] imgdata = i.getByteArrayExtra("image_data");
////        Bitmap bitmap = BitmapFactory.decodeByteArray(imgdata, 0, imgdata.length);
//        img_picFood.setImageBitmap(food.convertByteArrayToBitmap(food_dao.getAnh(fd)));
////        img_picFood.setImageBitmap(bitmap); holder.setCurrentImage(currentImage);
//        tv_numberOrder_showdetail.setText(String.valueOf(numberOrder));
//
//        int so =numberOrder;
//
//        img_plus_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                numberOrder = numberOrder + 1;
//                tv_numberOrder_showdetail.setText(String.valueOf(numberOrder));
//            }
//        });
//
//        img_minus_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (numberOrder>1){
//                    numberOrder = numberOrder - 1;
//                }else {
//                    numberOrder = 0;
//                }
//                tv_numberOrder_showdetail.setText(String.valueOf(numberOrder));
//
//            }
//        });
//        tv_addToCart_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                food.setNumberInCart(numberOrder);
//                managementCart.insertFood_2(food);
//                Intent intent = new Intent(ShowDetailActivity_2.this, Cart_Activity.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void initView() {
//        tv_addToCart_btn = findViewById(R.id.tv_addToCart_btn);
//        tv_title_showdetail = findViewById(R.id.tv_title_showdetail);
//        tv_price_showdetail = findViewById(R.id.tv_price_showdetail);
//        tv_numberOrder_showdetail = findViewById(R.id.tv_numberOrder_showdetail);
//        tv_description = findViewById(R.id.tv_description);
//        img_picFood = findViewById(R.id.img_picFood);
//        img_minus_btn = findViewById(R.id.img_minus_btn);
//        img_plus_btn = findViewById(R.id.img_plus_btn);
//
//    }
    private String getRoundPrice(){
        Intent i = getIntent();
        String fd = i.getStringExtra("object_2");
        food_dao = new Food_DAO(this);
        return Math.round(Integer.parseInt(food_dao.getGIA(fd)) * quantity) + " VNĐ";
    }



    private void referenceComponent(){
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(view -> this.finish());

        tvName = findViewById(R.id.tvFoodName);
        tvDescription = findViewById(R.id.tvDescription);
        tvPrice = findViewById(R.id.tvPrice);
        img_picFood = findViewById(R.id.image);

        tvQuantity = findViewById(R.id.tvFoodQuantity_Food);

        Button btnAddToCart = findViewById(R.id.btnAddToCart);
//        btnAddToCart.setOnClickListener(view -> {
//            // Make cart if don't have
//            Cursor cursor = dao.getCart(userID);
//            if (!cursor.moveToFirst()){
//                dao.addOrder(new Order(1, userID, "", "", 0d, "Craft"));
//                cursor = dao.getCart(userID);
//            }
//
//            // add order detail
//            cursor.moveToFirst();
//
//            OrderDetail orderDetail = dao.getExistOrderDetail(cursor.getInt(0), foodSize);
//            if(orderDetail != null) {
//                orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
//                if(dao.updateQuantity(orderDetail)){
//                    Toast.makeText(this, "Thêm món ăn vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
//                }
//
//            } else {
//                boolean addOrderDetail = dao.addOrderDetail(new OrderDetail(cursor.getInt(0),
//                        foodSize.getFoodId(), foodSize.getSize(), foodSize.getPrice(), quantity));
//
//                if(addOrderDetail){
//                    Toast.makeText(this, "Thêm món ăn vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        Button btnAddQuantity = findViewById(R.id.btnAddQuantity_Food);
        btnAddQuantity.setOnClickListener(view -> {
            quantity++;
            tvQuantity.setText(String.format("%s", quantity));
            tvPrice.setText(getRoundPrice());
        });

        Button btnSubQuantity = findViewById(R.id.btnSubQuantity_Food);
        btnSubQuantity.setOnClickListener(view -> {
            if(quantity > 1){
                quantity--;
                tvQuantity.setText(String.format("%s", quantity));
                tvPrice.setText(getRoundPrice());
            }
        });
    }

    private void SetPriceDefault(Double price){
        tvPrice.setText(getRoundPrice());
        quantity = 1;
        tvQuantity.setText("1");
    }

    private void LoadData(){
        Intent intent = getIntent();
        if(intent != null){
//            Food food = (Food) intent.getSerializableExtra("food");
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
//            img_picFood.setImageBitmap(food.convertByteArrayToBitmap(food_dao.getAnh(fd)));
            tvPrice.setText(getRoundPrice());
        }
    }
}