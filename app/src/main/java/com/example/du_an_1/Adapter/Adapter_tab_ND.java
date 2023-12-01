package com.example.du_an_1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.QuanLyND_DHD;
import com.example.du_an_1.Fragment.QuanLyND_NHD;

public class Adapter_tab_ND extends FragmentStateAdapter {
    int page = 2;
    QuanLyND_DHD frag_dangHD;
    QuanLyND_NHD frag_lsp_ngungHD;

    public Adapter_tab_ND(@NonNull Fragment fragment) {
        super(fragment);
        frag_dangHD = new QuanLyND_DHD();
        frag_lsp_ngungHD = new QuanLyND_NHD();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return frag_dangHD;
        } else {
            return frag_lsp_ngungHD;
        }
    }

    @Override
    public int getItemCount() {
        return page;
    }

    public void LoailoadData(){
        frag_dangHD.loadData();
    }
}
