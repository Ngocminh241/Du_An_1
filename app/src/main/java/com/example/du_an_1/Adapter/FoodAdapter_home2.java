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

import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.User;
import com.example.du_an_1.MainActivity;
import com.example.du_an_1.R;
import com.example.du_an_1.ShowDetailActivity_2;

import java.util.List;

public class FoodAdapter_home2 extends RecyclerView.Adapter<FoodAdapter_home2.ViewHolder>{
    public static int userID;
    Context context;
    List<Food> list;
    List<User> list2;
    MainActivity mainActivity;
    Food_DAO food_dao;
    User_DAO user_dao;
    private FoodAdapter_home2.ViewHolder currentViewHolder;


    public FoodAdapter_home2(Context context, List<Food> list) {
        this.context = context;
        this.list = list;
         mainActivity = (MainActivity) context;
        food_dao = new Food_DAO(context);
    }



    @NonNull
    @Override
    public FoodAdapter_home2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pizza,parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter_home2.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        user_dao = new User_DAO(context);
        holder.tv_title.setText(list.get(position).getTenFood());
        holder.tv_fee.setText(String.valueOf(list.get(position).getGiaFood()));
        holder.img_pic.setImageURI(list.get(position).hienthi(context));
        currentViewHolder = holder; // Lưu trữ holder hiện tại
        // Lưu trữ hình ảnh hiện tại của đối tượng đang được hiển thị
        byte[] currentImage = list.get(position).getHinhAnh();
        holder.setCurrentImage(currentImage);

        holder.tv_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity_2.class);
                intent.putExtra("object_2", list.get(position).getMaFood());
                intent.putExtra("image_data", list.get(position).getHinhAnh());
                intent.putExtra("user", String.valueOf(MainActivity.user));
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
            tv_title = itemView.findViewById(R.id.tv_title_food);
            tv_fee = itemView.findViewById(R.id.tv_fee);
            tv_btn_add = itemView.findViewById(R.id.tv_btn_add);
            img_pic = itemView.findViewById(R.id.img_pic);
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
