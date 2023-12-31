package com.example.du_an_1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.Helper.ManagementCart;
import com.example.du_an_1.Interface.ChangeNumberItemsListener;
import com.example.du_an_1.R;

import java.util.ArrayList;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.ViewHolder> {
    private ArrayList<Food> food;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public Cart_Adapter(ArrayList<Food> food, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.food = food;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public Cart_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);


        return new ViewHolder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull Cart_Adapter.ViewHolder holder, int position) {
        holder.tv_title_holder_cart.setText(food.get(position).getTenFood());
        holder.tv_Fee_EachItem.setText(String.valueOf(food.get(position).getGiaFood()));
        holder.tv_total_EachItem.setText(String.valueOf(((food.get(position).getNumberInCart() * food.get(position).getGiaFood())* 100/100)));
        holder.tv_numberItem_cart.setText(String.valueOf(food.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(String.valueOf(food.get(position).getHinhAnh()),
                "drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.img_pic_cart_holder);

        holder.img_plus_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood_2(food, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });


            }
        });

        holder.img_minus_cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood_2(food, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return food.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title_holder_cart, tv_Fee_EachItem, tv_total_EachItem, tv_numberItem_cart;
        ImageView img_pic_cart_holder, img_minus_cart_btn, img_plus_cart_btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title_holder_cart = itemView.findViewById(R.id.tv_title_holder_cart);
            tv_Fee_EachItem = itemView.findViewById(R.id.tv_Fee_EachItem);
            tv_total_EachItem = itemView.findViewById(R.id.tv_total_EachItem);
            tv_numberItem_cart = itemView.findViewById(R.id.tv_numberItem_cart);

            img_pic_cart_holder = itemView.findViewById(R.id.img_pic_cart_holder);
            img_minus_cart_btn = itemView.findViewById(R.id.img_minus_cart_btn);
            img_plus_cart_btn = itemView.findViewById(R.id.img_plus_cart_btn);
        }
    }
}
