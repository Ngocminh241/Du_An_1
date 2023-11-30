package com.example.du_an_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DTO.Type_Of_Food;
import com.example.du_an_1.R;

import java.util.ArrayList;

public class LoaiFood_SpinerAdapter extends ArrayAdapter<Type_Of_Food> {

    private Context context;
    ArrayList<Type_Of_Food> list;
    TextView tvMaLoaiFood, tvTenLoaiFood;
    Type_Of_Food_DAO type;
    int check;

    public LoaiFood_SpinerAdapter(@NonNull Context context, ArrayList<Type_Of_Food> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_food_spiner_adapter, null);
        }
        final Type_Of_Food item = list.get(position);
        if (item != null) {
                tvMaLoaiFood = v.findViewById(R.id.tvMaLoaiFood);
                tvMaLoaiFood.setText(item.getMaLoai()+ ". ");

                tvTenLoaiFood = v.findViewById(R.id.tvTenLoaiFood);
                tvTenLoaiFood.setText(item.getTenLoai());
            }


        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loai_food_spiner_adapter, null);

        }
        final Type_Of_Food item = list.get(position);
        if (item != null) {
                tvMaLoaiFood = v.findViewById(R.id.tvMaLoaiFood);
                tvMaLoaiFood.setText(item.getMaLoai()+ ". ");

                tvTenLoaiFood = v.findViewById(R.id.tvTenLoaiFood);
                tvTenLoaiFood.setText(item.getTenLoai());
        }
        return v;
    }
}