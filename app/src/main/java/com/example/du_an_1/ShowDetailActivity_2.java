package com.example.du_an_1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.du_an_1.Adapter.FoodAdapter;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.Domain.FoodDomain;
import com.example.du_an_1.Helper.ManagementCart;

import java.sql.Blob;
import java.util.List;

public class ShowDetailActivity_2 extends AppCompatActivity {
    private TextView tv_addToCart_btn;
    private TextView tv_title_showdetail, tv_price_showdetail, tv_numberOrder_showdetail, tv_description;
    private ImageView img_picFood;
    private ImageView img_minus_btn;
    private ImageView img_plus_btn;
    private FoodDomain object;
    private Food food;
    List<Food> list;
    private ManagementCart managementCart;
    int numberOrder = 1;
    Food_DAO food_dao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_2);

        managementCart = new ManagementCart(this);
        initView();
        getBundle2();
    }

    private void getBundle2() {
        Intent i = getIntent();
        String fd = i.getStringExtra("object_2");
//        food_dao.getAll();
        food_dao = new Food_DAO(this);
//        User thuThu = user_dao.getMaDN(user);
//        tv_name.setText("Hi " + username);
        tv_title_showdetail.setText(food_dao.getTenFood(fd));
        tv_price_showdetail.setText("VND " + food_dao.getGIA(fd));
        tv_description.setText(food_dao.getMOTA(fd));

            byte[] imgdata = i.getByteArrayExtra("image_data");
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgdata, 0, imgdata.length);
            img_picFood.setImageBitmap(bitmap);
        tv_numberOrder_showdetail.setText(String.valueOf(numberOrder));


        img_plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                tv_numberOrder_showdetail.setText(String.valueOf(numberOrder));
            }
        });

        img_minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder>1){
                    numberOrder = numberOrder - 1;
                }else {
                    numberOrder = 0;
                }
                tv_numberOrder_showdetail.setText(String.valueOf(numberOrder));

            }
        });
        tv_addToCart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                food.setNumberInCart(numberOrder);
                managementCart.insertFood_2(food);
                Intent intent = new Intent(ShowDetailActivity_2.this, Cart_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        tv_addToCart_btn = findViewById(R.id.tv_addToCart_btn);
        tv_title_showdetail = findViewById(R.id.tv_title_showdetail);
        tv_price_showdetail = findViewById(R.id.tv_price_showdetail);
        tv_numberOrder_showdetail = findViewById(R.id.tv_numberOrder_showdetail);
        tv_description = findViewById(R.id.tv_description);
        img_picFood = findViewById(R.id.img_picFood);
        img_minus_btn = findViewById(R.id.img_minus_btn);
        img_plus_btn = findViewById(R.id.img_plus_btn);

    }
}