package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.du_an_1.Adapter.CategoryAdapter;
import com.example.du_an_1.Domain.CategoryDomain;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewCategoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> categoryDomains = new ArrayList<>();
        categoryDomains.add(new CategoryDomain("Pizza", "cat_1"));
        categoryDomains.add(new CategoryDomain("Burger", "cat_2"));
        categoryDomains.add(new CategoryDomain("Hotdog", "cat_3"));
        categoryDomains.add(new CategoryDomain("Drink", "cat_4"));
        categoryDomains.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new CategoryAdapter(categoryDomains);
        recyclerViewCategoryList.setAdapter(adapter);

    }
}