package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.R;

public class QuanLyHoaDonFragment extends Fragment {

    RecyclerView rcvHD;
//    ReceiptDAO receiptDAO;
//    ReceiptAdapter adapter;
//    List<Receipt> list;


    public QuanLyHoaDonFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_hoa_don, container, false);
        rcvHD = view.findViewById(R.id.rcvHoaDon);
//        receiptDAO = new ReceiptDAO(getContext());
//        list = new ArrayList<>();
//        list = receiptDAO.selectAll();
//        adapter = new ReceiptAdapter(getContext(), list);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        rcvHD.setLayoutManager(linearLayoutManager);
//        rcvHD.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        return view;
    }
}