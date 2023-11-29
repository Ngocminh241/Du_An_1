package com.example.du_an_1.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.List_Category.Pizza_List;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;

import java.util.ArrayList;
import java.util.List;

public class Type_Of_Food_Adapter extends RecyclerView.Adapter<Type_Of_Food_Adapter.ViewHolder>{
    Context context;
    List<Type_Of_Food> list;
    Pizza_List pizzaList;
    MainActivity mainActivity;
    Type_Of_Food_DAO T_food_dao;
    ArrayList<Type_Of_Food> list2;
    String strLoai;
    private Type_Of_Food_Adapter.ViewHolder currentViewHolder;


    public Type_Of_Food_Adapter(Context context, List<Type_Of_Food> list) {
        this.context = context;
        this.list = list;
//        pizzaList = (Pizza_List) context;
        mainActivity = (MainActivity) context;
        T_food_dao = new Type_Of_Food_DAO(context);
    }


    @NonNull
    @Override
    public Type_Of_Food_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);


        return new Type_Of_Food_Adapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Type_Of_Food_Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_title.setText(list.get(position).getTenLoai());
        holder.img_pic.setImageURI(list.get(position).hienthi(context));
        currentViewHolder = holder; // Lưu trữ holder hiện tại
        // Lưu trữ hình ảnh hiện tại của đối tượng đang được hiển thị
        byte[] currentImage = list.get(position).getHinhAnh();
        holder.setCurrentImage(currentImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strLoai = list.get(position).getTenLoai();
                Intent intent = new Intent(holder.itemView.getContext(), Pizza_List.class);
                intent.putExtra("title", strLoai);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_fee, tv_btn_add,tv_loai_sp;
        ImageView img_pic;
        ConstraintLayout mainLayout;
        private byte[] currentImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.categoryName);
            img_pic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
        public void setCurrentImage(byte[] currentImage) {
            this.currentImage = currentImage;
        }

        public byte[] getCurrentImage() {
            return currentImage;
        }
    }
}

