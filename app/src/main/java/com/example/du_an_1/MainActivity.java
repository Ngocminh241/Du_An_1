package com.example.du_an_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.PopularAdapter;
import com.example.du_an_1.Adapter.Type_Of_Food_Adapter;
import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.DTO.User;
import com.example.du_an_1.Domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    User_DAO user_dao;
    User user;
    TextView tv_name;
    private RecyclerView.Adapter adapter1, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    List<User> listUser = new ArrayList<>();
    List<Type_Of_Food> list = new ArrayList<>();
    Type_Of_Food_DAO type_of_food_dao;
    Context context;
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

        list = new ArrayList<>();
        type_of_food_dao = new Type_Of_Food_DAO(this);
        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout settingBtn = findViewById(R.id.setting_btn);
        LinearLayout profile = findViewById(R.id.profile_btn);
        LinearLayout support = findViewById(R.id.support_btn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cart_Activity.class));
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Support.class));
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profile.class));

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

        list = type_of_food_dao.getAllTY(0);
        adapter1 = new Type_Of_Food_Adapter(this,list);
//        adapter_2 = new FoodAdapter(this, food);
        recyclerViewCategoryList.setAdapter(adapter1);
        context = (MainActivity) this;
//        adapter_2 = adapter_food;
        type_of_food_dao = new Type_Of_Food_DAO(this);
        adapter1.notifyDataSetChanged();

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