package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.Adapter.Adapter_ND_QL;
import com.example.du_an_1.DAO.User_DAO;
import com.example.du_an_1.DTO.User;
import com.example.du_an_1.R;

import java.util.List;


public class QuanLyND_NHD extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Adapter_ND_QL adapter_nd_ql;
    RecyclerView rc_list;
    List<User> list;
    User_DAO user_dao;

    public QuanLyND_NHD() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuanLySanPham_NKD_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuanLyND_NHD newInstance(String param1, String param2) {
        QuanLyND_NHD fragment = new QuanLyND_NHD();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_n_d__n_h_d, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rc_list = view.findViewById(R.id.rcv_ND_ngung);
        user_dao = new User_DAO(getContext());
        list = user_dao.getAllA(3);
        adapter_nd_ql = new Adapter_ND_QL(view.getContext(), list,3);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setAdapter(adapter_nd_ql);
        rc_list.setLayoutManager(linearLayoutManager);
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadData() {
        adapter_nd_ql.notifyDataSetChanged();
    }
}