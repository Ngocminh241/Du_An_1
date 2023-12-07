package com.example.du_an_1.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.DAO.DAO_GioHang;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.GioHang;
import com.example.du_an_1.DTO.chitietDonHang;
import com.example.du_an_1.Domain.OrderCard;
import com.example.du_an_1.R;
import com.example.du_an_1.ViewOrderActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import hcmute.edu.vn.phamdinhquochoa.foodyapp.HomeActivity;
//import hcmute.edu.vn.phamdinhquochoa.foodyapp.PaymentActivity;
//import hcmute.edu.vn.phamdinhquochoa.foodyapp.ViewOrderActivity;
//import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Food;
//import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Order;
//import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.OrderDetail;
//import hcmute.edu.vn.phamdinhquochoa.foodyapp.beans.Restaurant;
//import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.CartCard;
//import hcmute.edu.vn.phamdinhquochoa.foodyapp.components.OrderCard;

public class QuanLyHoaDonFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View mainView;
    @SuppressLint("StaticFieldLeak")
    public static LinearLayout cartContainer;
    private static String status;
    private LinearLayout btnDangDen, btnLichSu;
    private TextView tvGioHang, tvDangDen, tvLichSu;

    RecyclerView rcvHD;

    DAO_GioHang dao_gioHang;
    int mangdung;
    User_DAO user_dao;
    Food_DAO food_dao;
    DAO_chitietDonHang dao_chitietDonHang;
    List<chitietDonHang> list = new ArrayList<>();

    public QuanLyHoaDonFragment() {
    }
    public static QuanLyHoaDonFragment newInstance(String param1, String param2) {
        QuanLyHoaDonFragment fragment = new QuanLyHoaDonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Rename and change types of parameters
            String mParam1 = getArguments().getString(ARG_PARAM1);
            String mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_quan_ly_hoa_don, container, false);
        cartContainer = mainView.findViewById(R.id.cartContainer);
        user_dao = new User_DAO(getContext());
        dao_chitietDonHang = new DAO_chitietDonHang(getContext());
        dao_gioHang = new DAO_GioHang(getContext());
        food_dao = new Food_DAO(getContext());
        list = new ArrayList<>();

        referencesComponent();
        LoadOrder("craft");
        status = "craft";
        return mainView;
    }
    @SuppressLint("UseRequireInsteadOfGet")
    private void referencesComponent(){
        btnDangDen = mainView.findViewById(R.id.btnDangDen);
        btnDangDen.setOnClickListener(view->{
            resetAttribute();
            btnDangDen.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.color.main_color));
            tvDangDen.setTextColor(Color.WHITE);

            LoadOrder("coming");
        });

        btnLichSu = mainView.findViewById(R.id.btnLichSu);
        btnLichSu.setOnClickListener(view -> {
            resetAttribute();
            btnLichSu.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.color.main_color));
            tvLichSu.setTextColor(Color.WHITE);

            LoadOrder("history");
        });

        tvDangDen = mainView.findViewById(R.id.tvDangDen);
        tvLichSu = mainView.findViewById(R.id.tvLichSu);

    }

    private void LoadOrder(String type){
        user_dao = new User_DAO(getContext());
        food_dao = new Food_DAO(getContext());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("ID_USER_FILE", Context.MODE_PRIVATE);
        int usernameLogged = sharedPreferences.getInt("USER_ID", 0);
        mangdung = usernameLogged;
        status = type;
        cartContainer.removeAllViews();

        switch (type) {
            case "coming": {
                ArrayList<GioHang> orderArrayList = dao_gioHang.getOrderOfUser(mangdung, "Coming");
                if (orderArrayList.size() > 0) {
                    for (GioHang order : orderArrayList) {
                        OrderCard card = new OrderCard(getContext(), order);
                        card.setOnClickListener(view -> {
                            Intent intent = new Intent(getContext(), ViewOrderActivity.class);
                            intent.putExtra("order", order);
                            startActivity(intent);
                        });
                        cartContainer.addView(card);
                    }
                }
                break;
            }
            case "history": {
                ArrayList<GioHang> orderArrayList = dao_gioHang.getOrderOfUser(mangdung, "Delivered");
                if (orderArrayList.size() > 0) {
                    for (GioHang order : orderArrayList) {
                        OrderCard card = new OrderCard(getContext(), order);
                        card.setOnClickListener(view -> {
                            Intent intent = new Intent(getContext(), ViewOrderActivity.class);
                            intent.putExtra("order", order);
                            startActivity(intent);
                        });
                        cartContainer.addView(card);
                    }
                }
                break;
            }
        }
    }



    @SuppressLint("UseRequireInsteadOfGet")
    private void resetAttribute(){
        btnDangDen.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()),R.drawable.bg_white));
        btnLichSu.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.bg_white));

        tvLichSu.setTextColor(Color.BLACK);
        tvDangDen.setTextColor(Color.BLACK);
    }
}