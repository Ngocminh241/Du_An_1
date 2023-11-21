package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.CategoryAdapter;
import com.example.du_an_1.Adapter.PopularAdapter;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.User;
import com.example.du_an_1.Domain.CategoryDomain;
import com.example.du_an_1.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    User_DAO user_dao;
    User user;
    TextView tv_name;
    private RecyclerView.Adapter adapter1, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hiện tên người dùng
        tv_name = findViewById(R.id.tv_title_food);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        user_dao = new User_DAO(this);
//        User thuThu = user_dao.getMaDN(user);
        String username = user_dao.getTenTV(user);
//        tv_name.setText("Hi " + username);

        if (user == "Admin"){
            tv_name.setText("Hi Admin");
        } else {
            tv_name.setText("Hi " + username);
        }
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout settingBtn = findViewById(R.id.setting_btn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cart_Activity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recycler_categories);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryDomains = new ArrayList<>();
        categoryDomains.add(new CategoryDomain("Pizza", "cat_1"));
        categoryDomains.add(new CategoryDomain("Burger", "cat_2"));
        categoryDomains.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryDomains.add(new CategoryDomain("Drink", "cat_4"));
        categoryDomains.add(new CategoryDomain("Donut", "cat_5"));

        adapter1 = new CategoryAdapter(categoryDomains);
        recyclerViewCategoryList.setAdapter(adapter1);
        adapter1.getItemViewType(R.id.categoryPic);

    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recycler_popular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<FoodDomain> foodList = new ArrayList<>();
        foodList.add(new FoodDomain("Pepperoni pizza","pizza","slices pepperoni, mozzerella cheese, fresh oregano, ground black pepper, pizza sauce", 1000));
        foodList.add(new FoodDomain("Cheese Burger","pop_2","beef, Gouda cheese, Special Sauce, Lettuce, Tomato", 950));
        foodList.add(new FoodDomain("Vegetable pizza","pop_3","olive oil, Vegetable oil, pitted kalamata, cherry tomatoes, fresh oregano, basil", 850));

        adapter2 = new PopularAdapter(foodList);
        recyclerViewPopularList.setAdapter(adapter2);

    }
}