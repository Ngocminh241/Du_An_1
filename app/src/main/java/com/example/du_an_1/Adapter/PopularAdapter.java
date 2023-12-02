//package com.example.du_an_1.Adapter;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.du_an_1.Domain.FoodDomain;
//import com.example.du_an_1.R;
//import com.example.du_an_1.ShowDetailActivity;
//
//import java.util.ArrayList;
//
//public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
//    ArrayList<FoodDomain> popularFood;
//
//    public PopularAdapter(ArrayList<FoodDomain> popularFood) {
//        this.popularFood = popularFood;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
//        return new ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.tv_title.setText(popularFood.get(position).getTitle());
//        holder.tv_fee.setText(String.valueOf(popularFood.get(position).getFee()));
//        String picUrl = "";
//
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(), "drawable",holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.img_pic);
//
//        holder.tv_btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
//                intent.putExtra("object", popularFood.get(position));
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return popularFood.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        TextView tv_title, tv_fee, tv_btn_add;
//        ImageView img_pic;
//        ConstraintLayout mainLayout;
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tv_title = itemView.findViewById(R.id.tv_title);
//            tv_fee = itemView.findViewById(R.id.tv_fee);
//            tv_btn_add = itemView.findViewById(R.id.tv_btn_add);
//            img_pic = itemView.findViewById(R.id.img_pic);
//            mainLayout = itemView.findViewById(R.id.mainLayout);
//        }
//    }
//}
