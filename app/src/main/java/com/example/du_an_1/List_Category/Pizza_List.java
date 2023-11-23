package com.example.du_an_1.List_Category;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.CategoryAdapter;
import com.example.du_an_1.Adapter.FoodAdapter;
import com.example.du_an_1.Cart_Activity;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.Domain.CategoryDomain;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Pizza_List extends AppCompatActivity {
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView.Adapter adapter1, adapter_2;
    TextView tv_title_food;
    RecyclerView rcv_pizza;

    FloatingActionButton floatingCart;

     Food_DAO food_dao;
    FoodAdapter adapter_food;
    Context context;
    List<Food> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_list);
//        floatingAdd = findViewById(R.id.float_add_btn);
        tv_title_food = findViewById(R.id.tv_title_food);
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        tv_title_food.setText(title);

        list = new ArrayList<>();
        food_dao = new Food_DAO(this);
        

        recyclerViewPizza();
        recyclerViewCategory();
        bottomNavigation();

//        floatingAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                int drawableResourceId = R.drawable.anh_spl;
////                Uri drawableUri = new Uri.Builder()
////                        .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
////                        .authority(getResources().getResourcePackageName(drawableResourceId))
////                        .appendPath(getResources().getResourceTypeName(drawableResourceId))
////                        .appendPath(getResources().getResourceEntryName(drawableResourceId))
////                        .build();
////                selectedImage = drawableUri;
//                showDialogAdd();
//            }
//        });
    }
    private void bottomNavigation(){
        floatingCart = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);

        floatingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pizza_List.this, Cart_Activity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Pizza_List.this, MainActivity.class));
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

    private void recyclerViewPizza(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        rcv_pizza = findViewById(R.id.rcv_pizza);
        rcv_pizza.setLayoutManager(linearLayoutManager);
//        List<Food> food = new ArrayList<>();
//        food.add(new Food("F002",1,"Pizza sauce", 970,null,"abcd"));
//        food.add(new Food("F003",2,"Pizza sauce", 3970,null,"abcd"));
//        food.add(new Food("F004",1,"Pizza sauce_2", 1970,null,"abcd"));
        list = food_dao.getAll();
        adapter_2 = new FoodAdapter(this,list);
//        adapter_2 = new FoodAdapter(this, food);
        rcv_pizza.setAdapter(adapter_2);
        context = (Pizza_List) this;
//        adapter_2 = adapter_food;
        food_dao = new Food_DAO(this);
        adapter_2.notifyDataSetChanged();

    }


}