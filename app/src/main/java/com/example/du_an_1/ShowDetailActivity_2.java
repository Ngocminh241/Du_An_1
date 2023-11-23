package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.Domain.FoodDomain;
import com.example.du_an_1.Helper.ManagementCart;

public class ShowDetailActivity_2 extends AppCompatActivity {
    private TextView tv_addToCart_btn;
    private TextView tv_title_showdetail, tv_price_showdetail, tv_numberOrder_showdetail, tv_description;
    private ImageView img_picFood, img_minus_btn, img_plus_btn;
    private FoodDomain object;
    private Food food;
    private ManagementCart managementCart;
    int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail_2);

        managementCart = new ManagementCart(this);
        initView();
        getBundle2();
    }

    private void getBundle2() {
        food = (Food) getIntent().getSerializableExtra("object_2");
        int drawableResourceId = this.getResources().getIdentifier(String.valueOf((food.getHinhAnh())),"drawable_2",this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(img_picFood);
        tv_title_showdetail.setText(food.getTenFood());
        tv_price_showdetail.setText("VND" + food.getGiaFood());
        tv_description.setText(food.getMoTa());
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