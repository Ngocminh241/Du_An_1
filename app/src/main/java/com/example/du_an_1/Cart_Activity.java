package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.Cart_Adapter;
import com.example.du_an_1.Helper.ManagementCart;
import com.example.du_an_1.Interface.ChangeNumberItemsListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cart_Activity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;

    TextView tv_item_total_Fee, tv_delivery_Services_Fee, tv_tax_Fee, tv_total_Fee, tv_empty;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cart_Activity.this, Cart_Activity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cart_Activity.this, MainActivity.class));
            }
        });
    }
    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        tv_item_total_Fee = findViewById(R.id.tv_item_total_Fee);
        tv_delivery_Services_Fee = findViewById(R.id.tv_delivery_Services_Fee);
        tv_tax_Fee = findViewById(R.id.tv_tax_Fee);
        tv_total_Fee = findViewById(R.id.tv_total_Fee);
        tv_empty = findViewById(R.id.tv_empty);
        scrollView = findViewById(R.id.scrollView_cart);
    }

    private void initList(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new Cart_Adapter(managementCart.getListCart(),this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        });
        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            tv_empty.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            tv_empty.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void CalculateCart(){
        double percenTax = 0.02;
        double deliver = 10;

        tax = Math.ceil((managementCart.gettotalFee()*percenTax)*100)/100;
        double total = Math.ceil((managementCart.gettotalFee()+tax+deliver)*100)/100;
        double itemTotal = Math.ceil(managementCart.gettotalFee()*100)/100;

        tv_item_total_Fee.setText("$"+itemTotal);
        tv_tax_Fee.setText("$"+tax);
        tv_delivery_Services_Fee.setText("$"+deliver);
        tv_total_Fee.setText("$"+total);

    }
}