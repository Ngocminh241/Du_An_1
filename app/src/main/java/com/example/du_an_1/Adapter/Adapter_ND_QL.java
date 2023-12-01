package com.example.du_an_1.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.DTO.User;
import com.example.du_an_1.Fragment.Frag_load_3;
import com.example.du_an_1.Fragment.QuanLyND_DHD;
import com.example.du_an_1.Fragment.QuanLyND_NHD;
import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter_ND_QL extends RecyclerView.Adapter<Adapter_ND_QL.ViewHolder>{
    ArrayList<Food> food;
    Context context;
    int maLoai;
    List<User> list;
    NavigationQuanLy navigationQuanLy;
    User_DAO user_dao;
    ArrayList<Type_Of_Food> listLoaiFood;
    LoaiFood_SpinerAdapter spinnerAdapter;
    Type_Of_Food_DAO type_of_food_dao;
    QuanLyND_DHD quanLyND_dhd;
    QuanLyND_NHD quanLyND_nhd;
    private ViewHolder currentViewHolder;
    int trangthai = 0;
    public Adapter_ND_QL(Context context, List<User> list, int trangthai) {
        this.context = context;
        this.list = list;
        navigationQuanLy = (NavigationQuanLy) context;
        this.trangthai = trangthai;
        user_dao = new User_DAO(context);
        quanLyND_dhd = new QuanLyND_DHD();
        quanLyND_nhd = new QuanLyND_NHD();
    }

    @NonNull
    @Override
    public Adapter_ND_QL.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nd, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_ND_QL.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tv_Ten.setText("Tên: "+list.get(position).getHoTen());
        holder.tv_ma_DN.setText("Mã DN: "+list.get(position).getMaDN());
        holder.tv_MK.setText("MK: "+list.get(position).getMatKhau());
        holder.tv_SDT.setText("SDT: "+list.get(position).getsDT());

        if (trangthai ==0){
            holder.tv_btn_delete.setText("Stop");
        }else {
            holder.tv_btn_delete.setText("Restore");
        }

        holder.tv_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthai == 0) {
                    ngungKD();
                } else if (trangthai == 3){
                    khoiphuc();
                }
            }

            private void ngungKD() {
                User DTO_user = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("Xác nhận muốn ngừng hoạt động tài khoản này");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (user_dao.DeleteRow(DTO_user) > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            FragmentManager manager = navigationQuanLy.getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.framelayout, new Frag_load_3()).commit();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "Đã hủy thao tác", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
            private void khoiphuc() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn khôi phục sản phẩm này ?");
                builder.setTitle("Thông báo");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        User us = list.get(position);
                        if (user_dao.KhoiphucRow(us) > 0) {
                            Toast.makeText(context, "Khôi phục thành công", Toast.LENGTH_SHORT).show();
                            locSP(trangthai);
                            FragmentManager manager = navigationQuanLy.getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.framelayout, new Frag_load_3()).commit();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Khôi phục thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Ten, tv_ma_DN, tv_MK, tv_SDT, tv_btn_delete;
        ImageView img_pic;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Ten = itemView.findViewById(R.id.tv_Ten);
            tv_ma_DN = itemView.findViewById(R.id.tv_ma_DN);
            tv_MK = itemView.findViewById(R.id.tv_MK);
            tv_SDT = itemView.findViewById(R.id.tv_SDT);
            tv_btn_delete = itemView.findViewById(R.id.tv_btn_delete);
            img_pic = itemView.findViewById(R.id.img_pic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }

    }
    public void locSP(int check) {
        if (check == 0) {
            list.clear();
            list.addAll(user_dao.getAllA(trangthai));
            notifyDataSetChanged();
        } else if (check == 1) {
            list.clear();
            list.addAll(user_dao.getAllA(trangthai));
            notifyDataSetChanged();
        }
    }
}
