//package com.example.du_an_1;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.bumptech.glide.Glide;
//import com.example.du_an_1.DTO.Food;
//import com.example.du_an_1.Domain.FoodDomain;
//import com.example.du_an_1.Helper.ManagementCart;
//
//public class ShowDetailActivity extends AppCompatActivity {
//    private TextView tv_addToCart_btn;
//    private TextView tv_title_showdetail, tv_price_showdetail, tv_numberOrder_showdetail, tv_description;
//    private ImageView img_picFood, img_minus_btn, img_plus_btn;
//    private FoodDomain object;
//    private Food food;
//    private ManagementCart managementCart;
//    int numberOrder = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_detail);
//
//        managementCart = new ManagementCart(this);
//        initView();
//        getBundle();
//    }
//
//    private void getBundle() {
//        object = (FoodDomain) getIntent().getSerializableExtra("object");
//        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
//        Glide.with(this).load(drawableResourceId).into(img_picFood);
//        tv_title_showdetail.setText(object.getTitle());
//        tv_price_showdetail.setText("VND" + object.getFee());
//        tv_description.setText(object.getDescription());
//        tv_numberOrder_showdetail.setText(String.valueOf(numberOrder));
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
//                object.setNumberInCart(numberOrder);
//                managementCart.insertFood(object);
//                Intent intent = new Intent(ShowDetailActivity.this, Cart_Activity.class);
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
//}