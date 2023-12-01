package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;

public class Frag_load_3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag_load_3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationQuanLy quanLy = (NavigationQuanLy) getActivity();
        FragmentManager manager = quanLy.getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framelayout, new Fragment_ND_ql()).commit();
    }
}
