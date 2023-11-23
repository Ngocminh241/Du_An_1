package com.example.du_an_1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.QuanLySanPham_KD_Fragment;
import com.example.du_an_1.Fragment.QuanLySanPham_NKD_Fragment;

public class Adapter_tab_sp extends FragmentStateAdapter {
    int page = 2;
    QuanLySanPham_KD_Fragment frag_kingdoanh;
    QuanLySanPham_NKD_Fragment frag_sp_ngungkinhdoanh;

    public Adapter_tab_sp(@NonNull Fragment fragment) {
        super(fragment);
        frag_kingdoanh = new QuanLySanPham_KD_Fragment();
        frag_sp_ngungkinhdoanh = new QuanLySanPham_NKD_Fragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
           return frag_kingdoanh;
        } else {
            return frag_sp_ngungkinhdoanh;
        }
    }

    @Override
    public int getItemCount() {
        return page;
    }

    public void loadData(){
        frag_kingdoanh.loadData();
    }
}
