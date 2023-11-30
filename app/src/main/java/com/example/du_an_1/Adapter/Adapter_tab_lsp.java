package com.example.du_an_1.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.du_an_1.Fragment.QuanLyLoaiSanPhamKD;
import com.example.du_an_1.Fragment.QuanLySanLoaiPham_NKD_Fragment;

public class Adapter_tab_lsp extends FragmentStateAdapter {
    int page = 2;
    QuanLyLoaiSanPhamKD frag_loaikingdoanh;
    QuanLySanLoaiPham_NKD_Fragment frag_lsp_ngungkinhdoanh;

    public Adapter_tab_lsp(@NonNull Fragment fragment) {
        super(fragment);
        frag_loaikingdoanh = new QuanLyLoaiSanPhamKD();
        frag_lsp_ngungkinhdoanh = new QuanLySanLoaiPham_NKD_Fragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
           return frag_loaikingdoanh;
        } else {
            return frag_lsp_ngungkinhdoanh;
        }
    }

    @Override
    public int getItemCount() {
        return page;
    }

    public void LoailoadData(){
        frag_loaikingdoanh.loadData();
    }
}
